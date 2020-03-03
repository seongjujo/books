package jo.seongju.books.core.keyword;

import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.03 20:22
 */
public interface KeywordLogService {

    List<KeywordLog> list(String username) throws Exception;

    List<KeywordLog> list(String username, Sort sort) throws Exception;

    KeywordLog get(String keywordName, String username) throws Exception;

    KeywordLog save(Keyword keyword, String username, LocalDateTime lastSearchTime) throws Exception;
}
