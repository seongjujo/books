package jo.seongju.books.endpoint.support;

import jo.seongju.books.core.exception.BookApiException;
import jo.seongju.books.endpoint.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.03 05:50
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = null;

        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError objectError : objectErrors) {
            message = objectError.getDefaultMessage();
            break;
        }

        Response<?> response = Response.badRequest(message);

        return handleExceptionInternal(ex, response, headers, status, request);
    }

    @ExceptionHandler(BookApiException.class)
    public ResponseEntity<Object> handleBookApiException(BookApiException ex, WebRequest request) {

        Response<?> response = Response.serverError();

        return handleExceptionInternal(ex, response, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {

        Response<?> response = Response.badRequest();

        return handleExceptionInternal(ex, response, null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {

        Response<?> response = Response.serverError();

        return handleExceptionInternal(ex, response, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
