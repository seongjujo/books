package jo.seongju.books.core.keyword;

import jo.seongju.books.core.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Seongju Jo. On 2020.03.03 20:09
 */
@Entity
@Table(name = "Keyword_log",
        indexes = {
                        @Index(name = "idx_last_search_time", columnList = "last_search_time"),
                        @Index(name = "idx_log_key", columnList = "keyword_name, username", unique = true)})
public class KeywordLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_no")
    private Long logNo;

    @Column(name = "last_search_time", nullable = false)
    private LocalDateTime lastSearchTime;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "keyword_name")
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private User user;

    public Long getLogNo() {
        return logNo;
    }

    public void setLogNo(Long logNo) {
        this.logNo = logNo;
    }

    public LocalDateTime getLastSearchTime() {
        return lastSearchTime;
    }

    public void setLastSearchTime(LocalDateTime lastSearchTime) {
        this.lastSearchTime = lastSearchTime;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "KeywordLog{" +
                "logNo=" + logNo +
                ", lastSearchTime=" + lastSearchTime +
                ", createdTime=" + createdTime +
                ", keyword=" + keyword +
                ", user=" + user +
                '}';
    }

    public static KeywordLog create(Keyword keyword, User user, LocalDateTime lastSearchTime) {

        KeywordLog log = new KeywordLog();
        log.setKeyword(keyword);
        log.setUser(user);
        log.setLastSearchTime(lastSearchTime);
        log.setCreatedTime(LocalDateTime.now());

        return log;
    }
}
