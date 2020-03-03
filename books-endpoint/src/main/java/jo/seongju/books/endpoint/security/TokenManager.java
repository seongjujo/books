package jo.seongju.books.endpoint.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.01 21:13
 */
public class TokenManager {

    private static final Logger logger = LoggerFactory.getLogger(TokenManager.class);

    private static final String TOKEN_PATH = "/oauth/token";

    private RestTemplate restTemplate;

    /**
     * oauth 토큰 요청하기 위한 client id
     */
    private String clientId;

    /**
     * oauth 토큰 요청하기 위한 client secret
     */
    private String clientSecret;

    /**
     * 토큰 발급 url 의 상대 경로
     */
    private String tokenPath = TOKEN_PATH;

    /**
     * 토큰 발급, 갱신 등 관련 base url
     */
    private String baseUrl;

    /**
     * 토큰 발급 url. (baseUrl + newTokenPath) 의 조합. init 메소드에서 초기화.
     */
    private URI tokenUrl;

    public TokenManager(String clientId, String clientSecret, String baseUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.baseUrl = baseUrl;
    }

    @PostConstruct
    public void init() {
        //
        // token 생성하는 url 만듬
        //
        this.tokenUrl = URI.create(baseUrl + tokenPath);

        //
        // RestTemplate 초기화 실행
        //
        initRestTemplate();
    }

    public Token newToken(String username, String password) throws Exception {
        //
        // oauth 토큰 발급 요청하기 위한 파라미터
        //
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", username);
        body.add("password", password);
        body.add("grant_type", "password");

        ResponseEntity<Token> responseEntity = restTemplate.postForEntity(tokenUrl, body, Token.class);

        return responseEntity.getBody();
    }

    public Token refreshToken(String refreshToken) throws Exception {
        //
        // 토큰 갱신
        //
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", refreshToken);

        ResponseEntity<Token> responseEntity = restTemplate.postForEntity(tokenUrl, body, Token.class);

        return responseEntity.getBody();
    }

    private void initRestTemplate() {
        logger.info("### initRestTemplate: ");
        this.restTemplate = new RestTemplate();
        //
        // Http Basic 인증하기 위한 Interceptor 추가함
        //
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(new BasicAuthenticationInterceptor(clientId, clientSecret));
    }

    public void setTokenPath(String tokenPath) {
        this.tokenPath = tokenPath;
    }
}
