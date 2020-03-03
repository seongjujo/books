package jo.seongju.books.kakao.api;

/**
 * Created by Seongju Jo. On 2020.02.29 21:34
 */
public interface KakaoBookApi {

    /**
     * 책 검색
     * @param query 검색을 원하는 질의어. 필수
     * @param sort 결과 문서 정렬 방식.
     * @param page 결과 페이지 번호.
     * @param size 한 페이지에 보여질 문서의 개수.
     * @param target 검색 필드 제한
     * @return
     */
    KakaoSearchResponse<KakaoBook> searchBook(String query, SearchSort sort, Integer page, Integer size, SearchTarget target);
}
