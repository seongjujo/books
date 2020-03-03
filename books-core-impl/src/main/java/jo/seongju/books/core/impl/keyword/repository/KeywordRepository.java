package jo.seongju.books.core.impl.keyword.repository;

import jo.seongju.books.core.keyword.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Seongju Jo. On 2020.02.29 18:59
 */
public interface KeywordRepository extends JpaRepository<Keyword, String> {

    List<Keyword> findTop10ByOrderBySearchCountDesc();

    Keyword findByName(String name);
}
