package jo.seongju.books.naver.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Seongju Jo. On 2020.02.29 23:05
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverSearchResponse<T> {

    private long total;

    private long start;

    private long display;

    private List<T> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getDisplay() {
        return display;
    }

    public void setDisplay(long display) {
        this.display = display;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
