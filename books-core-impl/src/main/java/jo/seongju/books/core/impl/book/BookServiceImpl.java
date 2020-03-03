package jo.seongju.books.core.impl.book;

import jo.seongju.books.core.book.Book;
import jo.seongju.books.core.book.BookService;
import jo.seongju.books.core.exception.BookApiException;
import jo.seongju.books.core.keyword.KeywordService;
import jo.seongju.books.kakao.api.*;
import jo.seongju.books.naver.api.NaverBook;
import jo.seongju.books.naver.api.NaverBookApi;
import jo.seongju.books.naver.api.NaverSearchResponse;
import jo.seongju.books.naver.api.NaverSearchSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.03 06:24
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private KakaoBookApi kakaoBookApi;

    @Autowired
    private NaverBookApi naverBookApi;

    @Autowired
    private KeywordService keywordService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<Book> list(String username, String keyword, Pageable pageable) throws Exception {

        //
        // 키워드에 대한 업무로직을 우선적으로 처리한다.
        //
        keywordService.save(keyword, username, LocalDateTime.now());

        Page<Book> page = null;
        try {
            // 카카오 API를 통해 책 검색한다.
            page = listFromKakao(keyword, pageable);

        } catch (Exception e) {
            LOG.warn("### fail to get book from kakao");
            //
            // 카카오 API 실패일 경우, 네이버 API 사용
            //
            try {
                page = listFromNaver(keyword, pageable);

            } catch (Exception ex) {
                //
                // 네이버 API도 실패 될 경우 BookApiException 날린다. 따라서 KeywordService.save 에 대한 처리도 롤백이 된다.
                //
                throw new BookApiException(ex, "naver_book_api");
            }
        }

        return page;
    }

    private Page<Book> listFromKakao(String keyword, Pageable pageable) {

        KakaoSearchResponse<KakaoBook> kakaoSearchResponse = kakaoBookApi.searchBook(keyword, SearchSort.ACCURACY, pageable.getPageNumber(), pageable.getPageSize(), SearchTarget.TITLE);

        List<KakaoBook> kakaoBooks = kakaoSearchResponse.getDocuments();

        List<Book> books = new ArrayList<>();

        for (KakaoBook kakaoBook : kakaoBooks) {
            books.add(convert(kakaoBook));
        }

        return new PageImpl<>(books, pageable, kakaoSearchResponse.getMeta().getTotalCount());
    }

    private Page<Book> listFromNaver(String keyword, Pageable pageable) {

        int start = pageable.getPageNumber() * pageable.getPageSize();

        NaverSearchResponse<NaverBook> naverSearchResponse = naverBookApi.searchBook(keyword, NaverSearchSort.SIM, start, pageable.getPageSize());

        List<NaverBook> naverBooks = naverSearchResponse.getItems();

        List<Book> books = new ArrayList<>();

        for (NaverBook naverBook : naverBooks) {
            books.add(convert(naverBook));
        }

        return new PageImpl<>(books, pageable, naverSearchResponse.getTotal());
    }

    private Book convert(KakaoBook kakaoBook) {

        Book book = new Book();
        book.setAuthor(kakaoBook.getAuthors()[0]);
        book.setTitle(kakaoBook.getTitle());
        book.setContents(kakaoBook.getContents());
        book.setIsbn(kakaoBook.getIsbn());
        book.setPrice(kakaoBook.getPrice());
        book.setUrl(kakaoBook.getUrl());

        return book;
    }

    private Book convert(NaverBook naverBooks) {

        Book book = new Book();
        book.setAuthor(naverBooks.getAuthor());
        book.setTitle(naverBooks.getTitle());
        book.setContents(naverBooks.getDescription());
        book.setIsbn(naverBooks.getIsbn());
        book.setPrice(naverBooks.getPrice());
        book.setUrl(naverBooks.getLink());

        return book;
    }
}
