package jo.seongju.books.core.impl.user.repository;

import jo.seongju.books.core.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Seongju Jo. On 2020.02.29 18:31
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
