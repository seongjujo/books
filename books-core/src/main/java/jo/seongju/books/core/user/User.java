package jo.seongju.books.core.user;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Seongju Jo. On 2020.02.29 18:12
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.USER;
        this.createdTime = LocalDateTime.now();
    }


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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public static User create(String username, String password) {

        return new User(username, password);
    }

    public enum Role implements Serializable {
        USER, SUPER_USER
    }
}
