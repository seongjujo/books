package jo.seongju.books.core.impl.keyword;

import jo.seongju.books.core.impl.keyword.repository.KeywordRepository;
import jo.seongju.books.core.keyword.Keyword;
import jo.seongju.books.core.keyword.KeywordLogService;
import jo.seongju.books.core.keyword.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.02.29 19:37
 */
@Service("keywordService")
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordRepository repository;

    @Autowired
    private KeywordLogService logService;

    @Override
    public Keyword get(String name) throws Exception {

        return repository.findByName(name);
    }

    @Override
    public List<Keyword> listTop10() throws Exception {

        return repository.findTop10ByOrderBySearchCountDesc();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized Keyword save(String keywordName, String username, LocalDateTime searchTime) throws Exception {

        Keyword keyword = get(keywordName);

        if (keyword == null) {
            keyword = Keyword.create(keywordName);
        }

        long searchCount = keyword.getSearchCount();
        searchCount++;
        keyword.setSearchCount(searchCount);
        keyword = repository.save(keyword);

        logService.save(keyword, username, searchTime);

        return keyword;
    }
}
