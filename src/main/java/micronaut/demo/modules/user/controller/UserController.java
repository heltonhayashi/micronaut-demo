package micronaut.demo.modules.user.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import micronaut.demo.comum.exception.NotFoundException;
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

    @Get("/{id}")
    User get(Long id) {
        return userService.getUser(id);
    }

    @Put("/{id}")
    User update(Long id, @Body @Valid User user) {
        return userService.update(id, user);
    }

    @Post
    HttpResponse <User> register(@Body @Valid User user) {
        userService.save(user);
        return HttpResponse.created(user);
    }

    @Delete("/{id}")
    void delete(Long id) {
        userService.delete(id);
    }


}
