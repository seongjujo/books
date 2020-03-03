package jo.seongju.books.endpoint.support;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Collection;

/**
 * Created by Seongju Jo. On 2020.03.03 04:44
 */
public abstract class AuthenticationUtils {

    private AuthenticationUtils() {

    }

    public static String currentUsername() {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) getAuthentication().getUserAuthentication();

        return token.getName();
    }

    public static OAuth2Authentication getAuthentication() throws DisabledException {

        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            throw new DisabledException("#### not authenticated");
        }

        return authentication;
    }

    public static Collection<? extends GrantedAuthority> getAuthorities() {

        return  getAuthentication().getUserAuthentication().getAuthorities();
    }
}
