package jo.seongju.books.endpoint.model;

import jo.seongju.books.core.user.User;

/**
 * Created by Seongju Jo. On 2020.03.03 04:48
 */
public class UserModel {

    private String username;

    private String role;

    private UserModel(User user) {
        this.username = user.getUsername();
        this.role = user.getRole().name();
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public static UserModel create(User user) {
        if (user == null) {
            return null;
        }

        return new UserModel(user);
    }
}
