package jo.seongju.books.endpoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jo.seongju.books.core.keyword.Keyword;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.04 01:25
 */
public class KeywordModel {

    private String keyword;

    @JsonProperty("search_count")
    private long searchCount;

    public KeywordModel(Keyword keyword) {
        this.keyword = keyword.getName();
        this.searchCount = keyword.getSearchCount();
    }

    public String getKeyword() {
        return keyword;
    }

    public long getSearchCount() {
        return searchCount;
    }

    public static KeywordModel create(Keyword keyword) {
        if (keyword == null) {
            return null;
        }

        return new KeywordModel(keyword);
    }

    public static List<KeywordModel> create(List<Keyword> keywords) {

        if (keywords == null || keywords.isEmpty()) {
            return null;
        }

        List<KeywordModel> models = new ArrayList<>(keywords.size());

        for (Keyword keyword : keywords) {
            models.add(create(keyword));
        }

        return models;
    }
}
