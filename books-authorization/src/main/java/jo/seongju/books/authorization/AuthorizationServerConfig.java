package jo.seongju.books.authorization;

import jo.seongju.books.authorization.security.DefaultJwtAccessTokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Created by Seongju Jo. On 2020.03.01 23:11
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.resource-ids}")
    private String[] resourceIds;

    @Value("${security.oauth2.client.scope}")
    private String[] scopes;

    @Value("${security.oauth2.client.authorized-grant-types}")
    private String[] authorizedGrantTypes;

    @Value("${security.oauth2.resource.jwt.key-value}")
    private String jwtSecret;

    @Value("${security.oauth2.client.access-token-validity-seconds}")
    private Integer accessTokenValiditySeconds;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //
        // 토큰 유효 검증 URL 를 콜하기 위해 필요한 권한
        //
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
        configurer.authenticationManager(authenticationManager);
        configurer.tokenStore(tokenStore());
        configurer.accessTokenConverter(accessTokenConverter());
        //configurer.exceptionTranslator(new CustomWebResponseExceptionTranslator());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient(clientId)
                .secret(clientSecret)
                .scopes(scopes)
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .authorizedGrantTypes(authorizedGrantTypes)
                .resourceIds(resourceIds);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new DefaultJwtAccessTokenConverter();
        converter.setSigningKey(jwtSecret);
        return converter;
    }
}