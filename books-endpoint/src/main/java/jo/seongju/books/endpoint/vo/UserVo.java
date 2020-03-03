package jo.seongju.books.endpoint.vo;

import jo.seongju.books.endpoint.validation.NewUser;

/**
 * Created by Seongju Jo. On 2020.03.03 04:51
 */
@NewUser
public class UserVo {

    private String username;

    private String password;

    private String passwordConfirmed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmed() {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(String passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }
}
