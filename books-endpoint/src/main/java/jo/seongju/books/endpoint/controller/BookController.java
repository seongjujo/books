package jo.seongju.books.endpoint.controller;

import jo.seongju.books.core.book.Book;
import jo.seongju.books.core.book.BookService;
import jo.seongju.books.endpoint.model.BookModel;
import jo.seongju.books.endpoint.model.Response;
import jo.seongju.books.endpoint.support.ControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.03 05:27
 */
@RestController
@RequestMapping("/api/books")
public class BookController extends ControllerSupport {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService service;

    // 책 검색
    @GetMapping(params = {"keyword"})
    @ResponseStatus(HttpStatus.OK)
    public Response<List<BookModel>> list(String keyword, @PageableDefault(page = 1) Pageable pageable) throws Exception {

        if (LOG.isDebugEnabled()) {
            LOG.debug("### keyword: {}", keyword);
        }

        Page<Book> page = service.list(currentUsername(), keyword, pageable);

        List<BookModel> models = BookModel.create(page.getContent());

        return Response.ok(models)
                .metadata("page", page.getNumber())
                .metadata("size", page.getSize())
                .metadata("total", page.getTotalElements());
    }
}
