package jo.seongju.books.endpoint.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Created by Seongju Jo. On 2020.03.01 21:53
 */
public class Token {

    private static final String SCOPE_SEPARATOR = " ";

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("scope")
    private String scopeText;

    @JsonProperty("jti")
    private String jwtId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScopeText() {
        return scopeText;
    }

    public void setScopeText(String scopeText) {
        this.scopeText = scopeText;
    }

    public String getJwtId() {
        return jwtId;
    }

    public void setJwtId(String jwtId) {
        this.jwtId = jwtId;
    }

    /**
     * scopeText 를 배열로 바꿔 리턴함.
     *
     * @return
     */
    @JsonIgnore
    public String[] getScopeArray() {
        if (!StringUtils.hasText(scopeText)) {
            return null;
        }

        return toScopeArray(scopeText);
    }

    private static String[] toScopeArray(String scopeText) {
        Assert.hasText(scopeText, "### token scope 정보가 없습니다.");

        String[] splitScopes = scopeText.split(SCOPE_SEPARATOR);
        for (int i = 0; i < splitScopes.length; i++) {
            splitScopes[i] = splitScopes[i].trim();
        }

        return splitScopes;
    }

    @Override
    public String toString() {
        return "Token{" +
                ", accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", scopeText='" + scopeText + '\'' +
                ", jwtId='" + jwtId + '\'' +
                '}';
    }
}
