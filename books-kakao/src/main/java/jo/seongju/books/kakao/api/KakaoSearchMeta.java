package jo.seongju.books.kakao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Seongju Jo. On 2020.02.29 21:50
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoSearchMeta {

    @JsonProperty("is_end")
    private boolean end;

    @JsonProperty("total_count")
    private long totalCount;

    @JsonProperty("pageable_count")
    private long pageableCount;

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageableCount() {
        return pageableCount;
    }

    public void setPageableCount(long pageableCount) {
        this.pageableCount = pageableCount;
    }
}
