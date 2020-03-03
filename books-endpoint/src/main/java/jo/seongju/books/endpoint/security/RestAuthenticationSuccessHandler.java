package jo.seongju.books.endpoint.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jo.seongju.books.core.user.User;
import jo.seongju.books.endpoint.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Seongju Jo. On 2020.03.01 21:12
 */
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationSuccessHandler.class);

    private TokenManager tokenManager;

    private ObjectMapper objectMapper;

    public RestAuthenticationSuccessHandler(TokenManager tokenManager, ObjectMapper objectMapper) {
        this.tokenManager = tokenManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String password = (String) authentication.getCredentials();

        Token token = null;
        try {
            //logger.debug("### start new token. username: {}, password: {}", username, password);
            token = tokenManager.newToken(username, password);
        } catch (Exception e) {
            logger.error("### 새로운 토큰 생성 시 에러 발생. username: " + username, e);
            throw new ServletException(e);
        }

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(response.getWriter(), Response.ok(token));

        response.getWriter().close();
    }
}
