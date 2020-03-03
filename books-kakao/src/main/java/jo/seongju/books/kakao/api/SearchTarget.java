package jo.seongju.books.kakao.api;

/**
 * Created by Seongju Jo. On 2020.02.29 22:57
 */
public enum SearchTarget {

    TITLE("title"),
    ISBN("isbn"),
    PUBLISHER("publisher"),
    PERSON("person");

    private String value;

    SearchTarget(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
