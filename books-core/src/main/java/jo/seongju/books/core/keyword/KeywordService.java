package jo.seongju.books.core.keyword;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.02.29 18:47
 */
public interface KeywordService {

    Keyword get(String name) throws Exception;

    List<Keyword> listTop10() throws Exception;

    Keyword save(String keywordName, String username, LocalDateTime searchTime) throws Exception;
}
