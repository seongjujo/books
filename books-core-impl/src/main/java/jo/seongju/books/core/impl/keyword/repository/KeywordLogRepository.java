package jo.seongju.books.core.impl.keyword.repository;

import jo.seongju.books.core.keyword.KeywordLog;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.03 20:27
 */
public interface KeywordLogRepository extends JpaRepository<KeywordLog, Long> {

    @Query(" SELECT log FROM KeywordLog log " +
            "LEFT JOIN FETCH log.keyword keyword " +
            "LEFT JOIN FETCH log.user user " +
            "WHERE keyword.name = :keywordName " +
            "AND user.username = :username")
    KeywordLog findByKey(@Param("keywordName") String keywordName, @Param("username") String username);


    List<KeywordLog> findByUser_Username(String username, Sort sort);
}
