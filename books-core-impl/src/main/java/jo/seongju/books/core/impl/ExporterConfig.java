package jo.seongju.books.core.impl;

import jo.seongju.books.core.book.BookService;
import jo.seongju.books.core.keyword.KeywordLogService;
import jo.seongju.books.core.keyword.KeywordService;
import jo.seongju.books.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Seongju Jo. On 2020.03.01 23:28
 */
@Configuration
public class ExporterConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private KeywordLogService keywordLogService;

    @Autowired
    @Qualifier("userPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Bean(name = "/userExporter")
    public RemoteExporter userExporter() {

        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
        serviceExporter.setService(userService);
        serviceExporter.setServiceInterface(UserService.class);

        return serviceExporter;
    }

    @Bean(name = "/bookExporter")
    public RemoteExporter bookExporter() {

        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
        serviceExporter.setService(bookService);
        serviceExporter.setServiceInterface(BookService.class);

        return serviceExporter;
    }

    @Bean(name = "/keywordExporter")
    public RemoteExporter keywordExporter() {

        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
        serviceExporter.setService(keywordService);
        serviceExporter.setServiceInterface(KeywordService.class);

        return serviceExporter;
    }

    @Bean(name = "/keywordLogExporter")
    public RemoteExporter keywordLogExporter() {

        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
        serviceExporter.setService(keywordLogService);
        serviceExporter.setServiceInterface(KeywordLogService.class);

        return serviceExporter;
    }

    @Bean(name = "/userPasswordEncoderExporter")
    public RemoteExporter userPasswordEncoderExporter() {

        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
        serviceExporter.setService(passwordEncoder);
        serviceExporter.setServiceInterface(PasswordEncoder.class);

        return serviceExporter;
    }
}
