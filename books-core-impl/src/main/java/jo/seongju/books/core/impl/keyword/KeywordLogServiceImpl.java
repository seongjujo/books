package jo.seongju.books.core.impl.keyword;

import jo.seongju.books.core.impl.keyword.repository.KeywordLogRepository;
import jo.seongju.books.core.keyword.Keyword;
import jo.seongju.books.core.keyword.KeywordLog;
import jo.seongju.books.core.keyword.KeywordLogService;
import jo.seongju.books.core.user.User;
import jo.seongju.books.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.03 20:25
 */
@Service("keywordLogService")
public class KeywordLogServiceImpl implements KeywordLogService {

    @Autowired
    private KeywordLogRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public List<KeywordLog> list(String username) throws Exception {

        Sort sort = Sort.by(Sort.Direction.DESC, "lastSearchTime");

        return list(username, sort);
    }

    @Override
    public List<KeywordLog> list(String username, Sort sort) throws Exception {

        return repository.findByUser_Username(username, sort);
    }

    @Override
    public KeywordLog get(String keywordName, String username) throws Exception {

        return repository.findByKey(keywordName, username);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public KeywordLog save(Keyword keyword, String username, LocalDateTime lastSearchTime) throws Exception {

        KeywordLog log = get(keyword.getName(), username);

        if (log == null) {

            User user = userService.get(username);
            log = KeywordLog.create(keyword, user, lastSearchTime);

            return repository.save(log);
        }

        log.setLastSearchTime(lastSearchTime);

        return repository.save(log);
    }
}
