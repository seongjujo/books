package jo.seongju.books.endpoint.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jo.seongju.books.endpoint.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Seongju Jo. On 2020.03.03 01:16
 */
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationFailureHandler.class);

    private ObjectMapper objectMapper;

    public RestAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        logger.error("### authentication error.", exception);

        //
        // username 없을 경우
        //
        if (exception instanceof UsernameNotFoundException) {

            handleUsernameNotFoundException(request, response, (UsernameNotFoundException) exception);
        }

        //
        // 로그인제한 회원일 경우
        //
        /*else if (exception instanceof LockedUserException) {
            handleLockedUserException(request, response, (LockedUserException) exception);
        }*/

        //
        // password 잘못될 경우
        //
        else if (exception instanceof BadCredentialsException) {

            handleBadCredentialsException(request, response, (BadCredentialsException) exception);
        }

        //
        // 시스템 error 발생할 경우
        //
        else if (exception instanceof AuthenticationServiceException) {

            handleAuthenticationServiceException(request, response, (AuthenticationServiceException) exception);
        }

        //
        // 다른 예외 상황
        //
        else {

            Response<?> body = Response.of(40100, "unknown authentication error");

            writeResponse(response, body);
        }
    }

    private void handleUsernameNotFoundException(HttpServletRequest request, HttpServletResponse response, UsernameNotFoundException exception) throws IOException {

        Response<?> body = Response.badRequest();

        writeResponse(response, body);
    }


    private void handleBadCredentialsException(HttpServletRequest request, HttpServletResponse response, BadCredentialsException exception) throws IOException {


        Response<?> body = Response.badRequest();

        writeResponse(response, body);
    }

    private void handleAuthenticationServiceException(HttpServletRequest request, HttpServletResponse response, AuthenticationServiceException exception) throws IOException {

        Response<?> body = Response.badRequest();

        writeResponse(response, body);
    }

    private void writeResponse(HttpServletResponse response, Response<?> body) throws IOException {

        String jsonBody = objectMapper.writeValueAsString(body);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(jsonBody);
        response.getWriter().close();
    }
}
