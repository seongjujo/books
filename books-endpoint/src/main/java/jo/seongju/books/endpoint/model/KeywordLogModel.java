package jo.seongju.books.endpoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jo.seongju.books.core.keyword.KeywordLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.04 02:03
 */
public class KeywordLogModel {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private String keyword;

    private String username;

    @JsonProperty("last_search_time")
    private String lastSearchTime;

    public KeywordLogModel(KeywordLog keywordLog) {
        this.keyword = keywordLog.getKeyword().getName();
        this.username = keywordLog.getUser().getUsername();
        this.lastSearchTime = DATE_TIME_FORMATTER.format(keywordLog.getLastSearchTime());
    }

    public String getKeyword() {
        return keyword;
    }

    public String getUsername() {
        return username;
    }

    public String getLastSearchTime() {
        return lastSearchTime;
    }

    public static KeywordLogModel create(KeywordLog keywordLog) {

        if (keywordLog == null) {
            return null;
        }

        return new KeywordLogModel(keywordLog);
    }

    public static List<KeywordLogModel> create(List<KeywordLog> keywordLogs) {

        if (keywordLogs == null || keywordLogs.isEmpty()) {
            return null;
        }

        List<KeywordLogModel> models = new ArrayList<>(keywordLogs.size());

        for (KeywordLog log : keywordLogs) {
            models.add(create(log));
        }

        return models;
    }
}
