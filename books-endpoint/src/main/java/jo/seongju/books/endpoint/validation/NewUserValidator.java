package jo.seongju.books.endpoint.validation;

import jo.seongju.books.core.user.User;
import jo.seongju.books.core.user.UserService;
import jo.seongju.books.endpoint.vo.UserVo;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.util.regex.Pattern;

/**
 * Created by Seongju Jo. On 2020.03.03 04:58
 */
public class NewUserValidator implements ConstraintValidator<NewUser, UserVo> {

    @Autowired
    private UserService userService;

    private String usernameRegex;

    private int passwordMinLength;

    @Override
    public void initialize(NewUser annotation) {
        this.passwordMinLength = annotation.passwordMinLength();
        this.usernameRegex = annotation.usernameRegex();
    }

    @Override
    public boolean isValid(UserVo vo, ConstraintValidatorContext context) {

        String username = vo.getUsername();
        String password = vo.getPassword();
        String passwordConfirmed = vo.getPasswordConfirmed();

        /*
validation.user.username.empty=username cannot be empty
validation.user.username.invalid=invalid username
validation.user.username.used=already used username
validation.user.password.empty=password cannot be empty
validation.user.password.whitespace=password cannot be has whitespace
validation.user.password.length=password length least is {0}
validation.user.password.confirm=password confirmed not matched

         */
        if (!StringUtils.hasText(username)) {
            ConstraintValidatorUtils.createConstraintViolation(context, "{validation.user.username.empty}");
            return false;
        }

        if (!Pattern.matches(usernameRegex, username)) {
            ConstraintValidatorUtils.createConstraintViolation(context, "{validation.user.username.invalid}");
            return false;
        }

        if (!StringUtils.hasText(password)) {
            ConstraintValidatorUtils.createConstraintViolation(context, "{validation.user.password.empty}");
            return false;
        }

        if (StringUtils.containsWhitespace(password)) {
            ConstraintValidatorUtils.createConstraintViolation(context, "{validation.user.password.whitespace}");
            return false;
        }

        if (password.length() < passwordMinLength) {
            HibernateConstraintValidatorContext validatorContext = (HibernateConstraintValidatorContext) context;
            validatorContext.addMessageParameter("minLength", passwordMinLength);
            ConstraintValidatorUtils.createConstraintViolation(context, "{validation.user.password.length}");
            return false;
        }

        if (!password.equals(passwordConfirmed)) {
            ConstraintValidatorUtils.createConstraintViolation(context, "{validation.user.password.confirm}");
            return false;
        }

        User user = null;
        try {
            user = userService.get(username);
        } catch (Exception e) {
            throw new ValidationException("try again after a moment");
        }

        if (user != null) {
            ConstraintValidatorUtils.createConstraintViolation(context, "{validation.user.username.used}");
            return false;
        }

        return true;
    }
}
