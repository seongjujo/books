package jo.seongju.books.kakao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Seongju Jo. On 2020.02.29 21:48
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoSearchResponse<T> {

    private List<T> documents;

    private KakaoSearchMeta meta;

    public List<T> getDocuments() {
        return documents;
    }

    public void setDocuments(List<T> documents) {
        this.documents = documents;
    }

    public KakaoSearchMeta getMeta() {
        return meta;
    }

    public void setMeta(KakaoSearchMeta meta) {
        this.meta = meta;
    }
}
