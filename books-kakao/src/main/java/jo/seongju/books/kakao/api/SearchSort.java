package jo.seongju.books.kakao.api;

/**
 * Created by Seongju Jo. On 2020.02.29 22:57
 */
public enum SearchSort {

    ACCURACY("accuracy"),   // 정확도순
    LATEST("latest");       // 최신순

    private String value;

    SearchSort(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
