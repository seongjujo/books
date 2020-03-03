package jo.seongju.books.kakao.support;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by Seongju Jo. On 2020.02.29 22:15
 */
public class KakaoSearchAuthenticationInterceptor implements ClientHttpRequestInterceptor {

    private String authorizationHeaderValue;

    public KakaoSearchAuthenticationInterceptor(String appKey) {
        this.authorizationHeaderValue = "KakaoAK " + appKey;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders headers = request.getHeaders();

        if (!headers.containsKey("Authorization")) {
            headers.set("Authorization", authorizationHeaderValue);
        }

        return execution.execute(request, body);
    }
}
