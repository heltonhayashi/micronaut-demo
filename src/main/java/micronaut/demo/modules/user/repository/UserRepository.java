package micronaut.demo.modules.user.repository;

import micronaut.demo.modules.user.model.User;

import java.util.List;


public interface UserRepository {

    List <User> findAll();
}
