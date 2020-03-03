package jo.seongju.books.core.keyword;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Seongju Jo. On 2020.02.29 18:46
 */
@Entity
@Table(name = "keywords", indexes = {@Index(name = "idx_search_count", columnList = "search_count")})
public class Keyword implements Serializable {

    @Id
    private String name;

    @Column(name = "search_count", nullable = false)
    private long searchCount;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(long searchCount) {
        this.searchCount = searchCount;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public static Keyword create(String name) {
        Keyword keyword = new Keyword();
        keyword.setName(name);
        keyword.setCreatedTime(LocalDateTime.now());

        return keyword;
    }
}
