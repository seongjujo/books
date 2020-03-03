package jo.seongju.books.endpoint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Seongju Jo. On 2020.03.03 04:58
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NewUserValidator.class)
public @interface NewUser {

    String message() default "fail to register for membership";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String usernameRegex() default "^[a-zA-Z0-9._-]{3,}$";

    int passwordMinLength() default 3;
}
