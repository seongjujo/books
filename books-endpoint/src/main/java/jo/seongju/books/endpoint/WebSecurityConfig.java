package jo.seongju.books.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import jo.seongju.books.core.user.UserService;
import jo.seongju.books.endpoint.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Created by Seongju Jo. On 2020.03.01 19:16
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private String authenticateUrl = "/authenticate";

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Autowired
    @Qualifier("securityObjectMapper")
    private ObjectMapper securityObjectMapper;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(restAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").anonymous()
                .anyRequest().authenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(restAuthenticationProvider());

        //
        // 사용자 인증 성공하고 나서 비밀번호를 삭제한다, 현재 시스템에서는 토큰 받기 위해 인증 서버로 username, raw password 정보를 보내 줘야해서
        // 비밀번호 정보가 필요하다 따라서 삭제하면 안 된다.
        //
        auth.eraseCredentials(false);
    }

    public AuthenticationProvider restAuthenticationProvider() {

        AuthenticationProvider authenticationProvider = new RestAuthenticationProvider(userService, userPasswordEncoder);

        return authenticationProvider;
    }

    public RestAuthenticationProcessingFilter restAuthenticationProcessingFilter() throws Exception {
        RestAuthenticationProcessingFilter authenticationProcessingFilter = new RestAuthenticationProcessingFilter(authenticateUrl, securityObjectMapper);
        authenticationProcessingFilter.setAuthenticationManager(authenticationManager());
        authenticationProcessingFilter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler());
        authenticationProcessingFilter.setAuthenticationFailureHandler(restAuthenticationFailureHandler());

        return authenticationProcessingFilter;
    }

    public AuthenticationSuccessHandler restAuthenticationSuccessHandler() {

        RestAuthenticationSuccessHandler restAuthenticationSuccessHandler = new RestAuthenticationSuccessHandler(tokenManager, securityObjectMapper);

        return restAuthenticationSuccessHandler;
    }

    public AuthenticationFailureHandler restAuthenticationFailureHandler() {

        RestAuthenticationFailureHandler restAuthenticationFailureHandler = new RestAuthenticationFailureHandler(securityObjectMapper);

        return restAuthenticationFailureHandler;
    }
}
