package jo.seongju.books.endpoint.controller;

import jo.seongju.books.core.keyword.Keyword;
import jo.seongju.books.core.keyword.KeywordLog;
import jo.seongju.books.core.keyword.KeywordLogService;
import jo.seongju.books.core.keyword.KeywordService;
import jo.seongju.books.endpoint.model.KeywordLogModel;
import jo.seongju.books.endpoint.model.KeywordModel;
import jo.seongju.books.endpoint.model.Response;
import jo.seongju.books.endpoint.support.ControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.03 19:09
 */
@RestController
@RequestMapping("/api/keywords")
public class KeywordController extends ControllerSupport {

    @Autowired
    private KeywordService service;

    @Autowired
    private KeywordLogService logService;

    // 인기 키워드 목록
    @GetMapping("/top")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<KeywordModel>> listTopKeyword() throws Exception {

        List<Keyword> keywords = service.listTop10();

        return Response.ok(KeywordModel.create(keywords));
    }

    // 사용자 검색 히스토리
    @GetMapping("/history")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<KeywordLogModel>> listHistory() throws Exception {

        List<KeywordLog> logs = logService.list(currentUsername());

        return Response.ok(KeywordLogModel.create(logs));
    }
}
