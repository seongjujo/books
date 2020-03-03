package jo.seongju.books.endpoint;

import jo.seongju.books.core.book.BookService;
import jo.seongju.books.core.keyword.KeywordLogService;
import jo.seongju.books.core.keyword.KeywordService;
import jo.seongju.books.core.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Seongju Jo. On 2020.03.02 01:46
 */
@Configuration
public class RemoteConfig {

    @Value("${remoteServiceUrl.user}")
    private String userServiceUrl;

    @Value("${remoteServiceUrl.book}")
    private String bookServiceUrl;

    @Value("${remoteServiceUrl.keyword}")
    private String keywordServiceUrl;

    @Value("${remoteServiceUrl.keywordLog}")
    private String keywordLogServiceUrl;


    @Value("${remoteServiceUrl.userPasswordEncoder}")
    private String userPasswordEncoderUrl;

    @Bean
    public HttpInvokerProxyFactoryBean userService() {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceUrl(userServiceUrl);
        proxyFactoryBean.setServiceInterface(UserService.class);

        return proxyFactoryBean;
    }

    @Bean
    public HttpInvokerProxyFactoryBean bookService() {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceUrl(bookServiceUrl);
        proxyFactoryBean.setServiceInterface(BookService.class);

        return proxyFactoryBean;
    }

    @Bean
    public HttpInvokerProxyFactoryBean keywordService() {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceUrl(keywordServiceUrl);
        proxyFactoryBean.setServiceInterface(KeywordService.class);

        return proxyFactoryBean;
    }

    @Bean
    public HttpInvokerProxyFactoryBean keywordLogService() {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceUrl(keywordLogServiceUrl);
        proxyFactoryBean.setServiceInterface(KeywordLogService.class);

        return proxyFactoryBean;
    }

    @Bean
    public HttpInvokerProxyFactoryBean userPasswordEncoder() {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceUrl(userPasswordEncoderUrl);
        proxyFactoryBean.setServiceInterface(PasswordEncoder.class);

        return proxyFactoryBean;
    }
}
