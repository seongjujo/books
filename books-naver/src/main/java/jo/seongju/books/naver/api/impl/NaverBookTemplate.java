package jo.seongju.books.naver.api.impl;

import jo.seongju.books.naver.api.NaverBook;
import jo.seongju.books.naver.api.NaverBookApi;
import jo.seongju.books.naver.api.NaverSearchResponse;
import jo.seongju.books.naver.api.NaverSearchSort;
import jo.seongju.books.naver.support.NaverSearchAuthenticationInterceptor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Seongju Jo. On 2020.02.29 23:04
 */
public class NaverBookTemplate implements NaverBookApi {

    private RestTemplate restTemplate;

    private String apiUrl;

    public NaverBookTemplate(String apiUrl, String clientId, String clientSecret) {
        this.apiUrl = apiUrl;
        initRestTemplate(clientId, clientSecret);
    }

    @Override
    public NaverSearchResponse<NaverBook> searchBook(String query, NaverSearchSort sort, Integer start, Integer display) {

        Assert.hasText(query, "### query title must not be empty");

        String url = apiUrl + "?query=" + query;

        if (sort != null) {
            url += "&sort=" + sort.getValue();
        }
        if (start != null) {
            url += "&start=" + start;
        }
        if (display != null) {
            url += "&display=" + display;
        }

        ParameterizedTypeReference<NaverSearchResponse<NaverBook>> responseType = new ParameterizedTypeReference<NaverSearchResponse<NaverBook>>() {};
        ResponseEntity<NaverSearchResponse<NaverBook>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

        return response.getBody();
    }

    private void initRestTemplate(String clientId, String clientSecret) {

        ClientHttpRequestInterceptor interceptor = new NaverSearchAuthenticationInterceptor(clientId, clientSecret);

        this.restTemplate = new RestTemplate();
        this.restTemplate.getInterceptors().add(interceptor);
    }
}
