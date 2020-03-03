package jo.seongju.books.endpoint.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Seongju Jo. On 2020.03.01 19:19
 */
public class RestAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationProcessingFilter.class);

    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";

    private String usernameParam;
    private String passwordParam;

    private ObjectMapper objectMapper;

    public RestAuthenticationProcessingFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper) {
        this(defaultFilterProcessesUrl, objectMapper, USERNAME_PARAM, PASSWORD_PARAM);
    }

    public RestAuthenticationProcessingFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper, String usernameParam, String passwordParam) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
        this.usernameParam = usernameParam;
        this.passwordParam = passwordParam;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        JsonNode jsonNode = objectMapper.readValue(request.getInputStream(), JsonNode.class);
        String username = jsonNode.path(usernameParam).asText();
        String password = jsonNode.path(passwordParam).asText();

        if (logger.isDebugEnabled()) {
            logger.debug("### username: {}, password: [******]", username);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authentication);
    }
}
