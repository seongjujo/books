package jo.seongju.books.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Seongju Jo. On 2020.03.04 04:29
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private MessageSource messageSource;

    @Override
    public Validator getValidator() {

        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);

        return bean;
    }
}