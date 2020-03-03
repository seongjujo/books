package jo.seongju.books.core.impl;

import jo.seongju.books.kakao.api.KakaoBookApi;
import jo.seongju.books.kakao.api.impl.KakaoBookTemplate;
import jo.seongju.books.naver.api.NaverBookApi;
import jo.seongju.books.naver.api.impl.NaverBookTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Seongju Jo. On 2020.03.03 00:30
 */
@Configuration
public class CoreConfig {

    @Value("${api.kakao.apiUrl}")
    private String kakaoApiUrl;

    @Value("${api.kakao.appKey}")
    private String kakaoAppKey;

    @Value("${api.naver.apiUrl}")
    private String naverApiUrl;

    @Value("${api.naver.clientId}")
    private String naverClientId;

    @Value("${api.naver.clientSecret}")
    private String naverClientSecret;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public KakaoBookApi kakaoBookApi() {

        KakaoBookApi api = new KakaoBookTemplate(kakaoApiUrl, kakaoAppKey);

        return api;
    }

    @Bean
    public NaverBookApi naverBookApi() {

        NaverBookApi api = new NaverBookTemplate(naverApiUrl, naverClientId, naverClientSecret);

        return api;
    }
}
