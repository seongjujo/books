package jo.seongju.books.naver.support;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by Seongju Jo. On 2020.02.29 23:18
 */
public class NaverSearchAuthenticationInterceptor implements ClientHttpRequestInterceptor {

    private static final String CLIENT_ID_HEADER_NAME = "X-Naver-Client-Id";
    private static final String CLIENT_SECRET_HEADER_NAME = "X-Naver-Client-Secret";

    private String clientId;
    private String clientSecret;

    public NaverSearchAuthenticationInterceptor(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.set(CLIENT_ID_HEADER_NAME, clientId);
        headers.set(CLIENT_SECRET_HEADER_NAME, clientSecret);

        return execution.execute(request, body);
    }
}
