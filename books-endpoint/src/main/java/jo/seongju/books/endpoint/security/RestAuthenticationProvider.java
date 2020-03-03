package jo.seongju.books.endpoint.security;

import jo.seongju.books.core.user.User;
import jo.seongju.books.core.user.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.01 21:21
 */
public class RestAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public RestAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();

        User user = null;
        try {
            user = userService.get(username);
        } catch (Exception e) {
            throw new AuthenticationServiceException("### failed to get user. username: " + username, e);
        }

        //
        // 사용자 정보 확인
        //
        if (user == null) {
            throw new UsernameNotFoundException("### 없는 사용자, username: " + username);
        }

        //
        // 비멸번호 정확한 지 확인
        //
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("### 정확한 비밀번호가 아닙니다.");
        }

        //
        // 사용자 권한 샛팅 리턴
        //
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
