package jo.seongju.books.core.user;

/**
 * Created by Seongju Jo. On 2020.02.29 18:12
 */
public interface UserService {

    User get(String username) throws Exception;

    User add(String username, String password) throws Exception;
}
