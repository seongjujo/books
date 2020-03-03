package jo.seongju.books.naver.api;

/**
 * Created by Seongju Jo. On 2020.02.29 23:16
 */
public enum NaverSearchSort {

    SIM("sim"),   // 정확도순
    DATE("date");       // 최신순

    private String value;

    NaverSearchSort(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
