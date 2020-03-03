package jo.seongju.books.authorization.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * Created by Seongju Jo. On 2020.03.01 23:19
 */
public class DefaultJwtAccessTokenConverter extends JwtAccessTokenConverter {

    private static final Logger logger = LoggerFactory.getLogger(DefaultJwtAccessTokenConverter.class);

    private JsonParser objectMapper = JsonParserFactory.create();

    @Value("${security.oauth2.resource.jwt.key-value}")
    private String jwtSecret;

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {

        Map<String, Object> response = (Map<String, Object>) super.convertAccessToken(token, authentication);
        response.put("active", true);

        return response;
    }

    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        String content;
        try {
            content = objectMapper.formatMap(convertAccessToken(accessToken, authentication));
        }
        catch (Exception e) {
            throw new IllegalStateException("Cannot convert access token to JSON", e);
        }

        MacSigner signer = createMacSigner();

        return JwtHelper.encode(content, signer).getEncoded();
    }

    @Override
    protected Map<String, Object> decode(String token) {

        try {

            Jwt jwt = JwtHelper.decode(token);

            String content = jwt.getClaims();

            Map<String, Object> map = objectMapper.parseMap(content);

            MacSigner signer = createMacSigner();

            jwt.verifySignature(signer);

            if (map.containsKey(EXP) && map.get(EXP) instanceof Integer) {
                Integer intValue = (Integer) map.get(EXP);
                map.put(EXP, new Long(intValue));
            }
            return map;
        }
        catch (Exception e) {
            throw new InvalidTokenException("Cannot convert access token to JSON", e);
        }
    }

    private MacSigner createMacSigner() {

        return new MacSigner(jwtSecret);
    }
}
