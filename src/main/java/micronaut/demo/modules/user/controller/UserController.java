package micronaut.demo.modules.user.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import micronaut.demo.modules.user.model.User;
import micronaut.demo.modules.user.service.UserService;

import java.util.List;

@Controller("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Get("/")
    List <User> all() {
        return userService.findAll();
    }
}
