package jo.seongju.books.authorization.security;

import jo.seongju.books.core.user.User;
import jo.seongju.books.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Seongju Jo. On 2020.03.01 23:15
 */
@Component("defaultUserDetailsService")
public class DefaultUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;

        try {
            user = userService.get(username);
        } catch (Exception e) {
            throw new AuthorizationServiceException("### error from getting user by username: " + username, e);
        }

        if (user == null) {
            throw new UsernameNotFoundException("### have no user : [" + username + "]");
        }

        return new DefaultUserDetails(user);
    }
}
