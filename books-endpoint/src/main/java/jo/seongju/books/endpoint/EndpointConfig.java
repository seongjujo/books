package jo.seongju.books.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import jo.seongju.books.endpoint.security.TokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Created by Seongju Jo. On 2020.03.03 01:37
 */
@Configuration
public class EndpointConfig {

    @Value("${oauth2.client-id}")
    private String clientId;

    @Value("${oauth2.client-secret}")
    private String clientSecret;

    @Value("${oauth2.oauth-base-url}")
    private String oauthBaseUrl;

    @Bean
    public TokenManager tokenManager() {

        return new TokenManager(clientId, clientSecret, oauthBaseUrl);
    }

    @Bean
    public ObjectMapper securityObjectMapper() {

        return new ObjectMapper();
    }


    @Bean
    public RemoteTokenServices remoteTokenServices() {

        String checkTokenUrl = "/oauth/check_token";
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(oauthBaseUrl + checkTokenUrl);
        remoteTokenServices.setClientId(clientId);
        remoteTokenServices.setClientSecret(clientSecret);

        return remoteTokenServices;
    }

    @Bean
    public AuthenticationEntryPoint oauth2AuthenticationEntryPoint() {

        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();

        //authenticationEntryPoint.setExceptionRenderer(new CustomDefaultOAuth2ExceptionRenderer());

        return authenticationEntryPoint;
    }

    /**
     * CORS (Cross-Origin Resource Sharing)  관련 설정.
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        //
        // 모든 헤더 허용
        //
        configuration.addAllowedHeader(CorsConfiguration.ALL);

        //
        // 허용 origin
        //
        //configuration.setAllowedOrigins(Arrays.asList(corsAllowedOrigins));

        //
        // 허용 메소드
        //
        configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.OPTIONS.name(), HttpMethod.HEAD.name()));

        //
        // 토큰 갱신시 필요한 X-Refresh-Token 설정 되어 있음
        //
        //configuration.setExposedHeaders(Arrays.asList(corsExposedHeaders));

        //
        // if true means use cookie such as session id. 현재는 쿠키 사용하지 않아서 false 로 함
        //
        configuration.setAllowCredentials(true);

        //
        // how long, in seconds, the response from a pre-flight request can be cached by clients.
        //
        //configuration.setMaxAge(corsMaxAge);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
