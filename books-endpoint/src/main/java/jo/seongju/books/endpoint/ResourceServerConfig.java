package jo.seongju.books.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Created by Seongju Jo. On 2020.03.01 19:32
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value(("${oauth2.resource-id}"))
    private String resourceId;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Autowired
    private RemoteTokenServices remoteTokenServices;

    @Autowired
    private AuthenticationEntryPoint oauth2AuthenticationEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId);
        resources.tokenServices(remoteTokenServices);
        resources.authenticationEntryPoint(oauth2AuthenticationEntryPoint);
        resources.stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest().authenticated();
    }

}
