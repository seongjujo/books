package jo.seongju.books.kakao.api.impl;

import jo.seongju.books.kakao.api.*;
import jo.seongju.books.kakao.support.KakaoSearchAuthenticationInterceptor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Seongju Jo. On 2020.02.29 21:34
 */
public class KakaoBookTemplate implements KakaoBookApi {

    private RestTemplate restTemplate;

    private String apiUrl;

    public KakaoBookTemplate(String apiUrl, String appKey) {
        this.apiUrl = apiUrl;
        initRestTemplate(appKey);
    }

    @Override
    public KakaoSearchResponse<KakaoBook> searchBook(String query, SearchSort sort, Integer page, Integer size, SearchTarget target) {

        Assert.hasText(query, "### query title must not be empty");

        String url = apiUrl + "?query=" + query;

        if (sort != null) {
            url += "&sort=" + sort.getValue();
        }
        if (page != null) {
            url += "&page=" + page;
        }
        if (size != null) {
            url += "&size=" + size;
        }

        ParameterizedTypeReference<KakaoSearchResponse<KakaoBook>> responseType = new ParameterizedTypeReference<KakaoSearchResponse<KakaoBook>>() {};
        ResponseEntity<KakaoSearchResponse<KakaoBook>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

        return response.getBody();
    }

    private void initRestTemplate(String appKey) {

        ClientHttpRequestInterceptor interceptor = new KakaoSearchAuthenticationInterceptor(appKey);

        this.restTemplate = new RestTemplate();
        this.restTemplate.getInterceptors().add(interceptor);
    }
}
