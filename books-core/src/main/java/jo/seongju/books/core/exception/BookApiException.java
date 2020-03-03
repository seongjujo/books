package jo.seongju.books.core.exception;

/**
 * Created by Seongju Jo. On 2020.03.03 23:48
 */
public class BookApiException extends RuntimeException {

    private String apiName;

    public BookApiException(Throwable cause, String apiName) {
        super(cause);
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }
}
