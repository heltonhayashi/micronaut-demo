package micronaut.demo.modules.user.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import micronaut.demo.modules.user.model.User;
import micronaut.demo.modules.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Get
    List <User> all() {
        return userService.findAll();
    }

    @Post
    HttpResponse <User> register(@Body @Valid User user) {
        userService.save(user);
        return HttpResponse.created(user);
    }

}
