package jo.seongju.books.core.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Seongju Jo. On 2020.02.29 18:15
 */
public interface BookService {

    Page<Book> list(String username, String keyword, Pageable pageable) throws Exception;
}
