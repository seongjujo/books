package jo.seongju.books.endpoint.validation;

import javax.validation.ConstraintValidatorContext;

/**
 * Created by Seongju Jo. On 2020.03.03 05:20
 */
public abstract class ConstraintValidatorUtils {

    private ConstraintValidatorUtils() {
    }

    public static void createConstraintViolation(ConstraintValidatorContext context, String errorCode) {
        createConstraintViolation(context, errorCode, null);
    }

    public static void createConstraintViolation(ConstraintValidatorContext context, String errorCode, String[] nodes) {
        context.disableDefaultConstraintViolation();
        addConstraintViolation(context, errorCode, nodes);
    }

    public static void addConstraintViolation(ConstraintValidatorContext context, String errorCode) {
        addConstraintViolation(context, errorCode, null);
    }

    public static void addConstraintViolation(ConstraintValidatorContext context, String errorCode, String[] nodes) {
        ConstraintValidatorContext.ConstraintViolationBuilder builder = context.buildConstraintViolationWithTemplate(errorCode);
        if (nodes != null && nodes.length > 0) {
            for (String node : nodes) {
                builder.addPropertyNode(node);
            }
        }
        builder.addConstraintViolation();
    }
}
