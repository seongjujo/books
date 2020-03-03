package jo.seongju.books.endpoint.support;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Collection;

/**
 * Created by Seongju Jo. On 2020.03.03 04:43
 */
public abstract class ControllerSupport {

    protected String currentUsername() {

        return AuthenticationUtils.currentUsername();
    }

    protected OAuth2Authentication getAuthentication() throws DisabledException {

        return AuthenticationUtils.getAuthentication();
    }

    protected Collection<? extends GrantedAuthority> getAuthorities() {

        return  AuthenticationUtils.getAuthorities();
    }
}
