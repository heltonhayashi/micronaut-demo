package micronaut.demo.modules.user.repository;

import micronaut.demo.modules.user.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


public interface UserRepository {

    Optional <User> findById(@NotNull Long id);

    User save(@NotBlank User user);

    List <User> findAll();
}
