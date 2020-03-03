package jo.seongju.books.core.impl.user;

import jo.seongju.books.core.impl.user.repository.UserRepository;
import jo.seongju.books.core.user.User;
import jo.seongju.books.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Seongju Jo. On 2020.02.29 19:07
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    @Qualifier("userPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public User get(String username) throws Exception {

        return repository.findByUsername(username);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User add(String username, String password) throws Exception {

        User user = User.create(username, passwordEncoder.encode(password));

        user = repository.save(user);

        return user;
    }
}
