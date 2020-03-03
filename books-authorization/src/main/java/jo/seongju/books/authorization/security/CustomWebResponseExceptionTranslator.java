package jo.seongju.books.authorization.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

/**
 * Created by Seongju Jo. On 2020.03.01 23:25
 */
public class CustomWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    private static final Logger logger = LoggerFactory.getLogger(CustomWebResponseExceptionTranslator.class);

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

        logger.debug("### CustomWebResponseExceptionTranslator.translate", e);

        ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
        OAuth2Exception oAuth2Exception = responseEntity.getBody();

        if (oAuth2Exception instanceof InvalidTokenException) {
            logger.debug("### InvalidTokenException");

            String message = oAuth2Exception.getMessage();
            if (message.contains("Token was not recognised")) {
                logger.debug("### Token was not recognised");
                oAuth2Exception.addAdditionalInformation("error_code", "490");
                oAuth2Exception.addAdditionalInformation("error_msg", "Token was not recognised");
            }
            else if (message.contains("Token has expired")) {
                logger.debug("### Token has expired");
                oAuth2Exception.addAdditionalInformation("error_code", "491");
                oAuth2Exception.addAdditionalInformation("error_msg", "Token has expired");
            }
            else if (message.contains("Cannot convert access token to JSON")) {
                logger.debug("### Cannot convert access token to JSON");
                oAuth2Exception.addAdditionalInformation("error_code", "492");
                oAuth2Exception.addAdditionalInformation("error_msg", "Cannot convert access token to JSON");
            }
            else {
                logger.debug("### Other InvalidTokenException");
                oAuth2Exception.addAdditionalInformation("error_code", "493");
                oAuth2Exception.addAdditionalInformation("error_msg", "Other InvalidTokenException");
            }
        }

        return responseEntity;
    }
}
