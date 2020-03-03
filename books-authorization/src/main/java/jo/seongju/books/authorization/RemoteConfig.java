package jo.seongju.books.authorization;

import jo.seongju.books.core.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Seongju Jo. On 2020.03.01 23:32
 */
@Configuration
public class RemoteConfig {

    @Value("${remoteServiceUrl.user}")
    private String userServiceUrl;

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
    public HttpInvokerProxyFactoryBean userPasswordEncoder() {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceUrl(userPasswordEncoderUrl);
        proxyFactoryBean.setServiceInterface(PasswordEncoder.class);

        return proxyFactoryBean;
    }
}
