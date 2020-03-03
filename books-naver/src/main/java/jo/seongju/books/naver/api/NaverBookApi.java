package jo.seongju.books.naver.api;

/**
 * Created by Seongju Jo. On 2020.02.29 23:02
 */
public interface NaverBookApi {

    NaverSearchResponse<NaverBook> searchBook(String query, NaverSearchSort sort, Integer start, Integer display);
}
