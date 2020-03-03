package jo.seongju.books.endpoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seongju Jo. On 2020.03.01 19:04
 */
public class Response<T> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @JsonProperty("status")
    private int status;

    @JsonProperty("current_time")
    private String currentTime;

    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    @JsonProperty("content")
    private T content;

    protected Response(int status, T content) {
        this.status = status;
        this.content = content;
        this.currentTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    public static <T> Response<T> of(int status, T content) {

        return new Response<>(status, content);
    }

    public static <T> Response<T> ok() {

        return ok(null);
    }

    public static <T> Response<T> ok(T content) {

        return of(HttpStatus.OK.value(), content);
    }

    public static <T> Response<T> created() {

        return created(null);
    }

    public static <T> Response<T> created(T content) {

        return of(HttpStatus.CREATED.value(), content);
    }

    public static <T> Response<T> badRequest() {

        return badRequest(null);
    }

    public static <T> Response<T> badRequest(T content) {

        return of(HttpStatus.BAD_REQUEST.value(), content);
    }

    public static <T> Response<T> serverError() {

        return serverError(null);
    }

    public static <T> Response<T> serverError(T content) {

        return of(HttpStatus.INTERNAL_SERVER_ERROR.value(), content);
    }

    public int getStatus() {
        return status;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public T getContent() {
        return content;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public Response<T> metadata(String key, Object value) {

        if (metadata == null) {
            metadata = new HashMap<>();
        }

        metadata.put(key, value);

        return this;
    }
}
