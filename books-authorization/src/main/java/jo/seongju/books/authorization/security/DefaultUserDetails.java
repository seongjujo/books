package jo.seongju.books.authorization.security;

import jo.seongju.books.core.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.01 23:14
 */
public class DefaultUserDetails implements UserDetails {

    private User user;

    private List<SimpleGrantedAuthority> authorities;

    public DefaultUserDetails(User user) {
        this.user = user;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(this.user.getRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public User getUser() {

        return user;
    }
}
