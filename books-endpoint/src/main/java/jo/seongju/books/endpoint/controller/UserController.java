package jo.seongju.books.endpoint.controller;

import jo.seongju.books.core.user.User;
import jo.seongju.books.core.user.UserService;
import jo.seongju.books.endpoint.model.Response;
import jo.seongju.books.endpoint.model.UserModel;
import jo.seongju.books.endpoint.support.ControllerSupport;
import jo.seongju.books.endpoint.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Seongju Jo. On 2020.03.03 02:30
 */
@RestController
@RequestMapping("/users")
public class UserController extends ControllerSupport {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    // 회원가입
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<UserModel> post(@Valid @RequestBody UserVo vo, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        User user = service.add(vo.getUsername(), vo.getPassword());

        return Response.created(UserModel.create(user));
    }
}
