package micronaut.demo.modules.user.service;

import micronaut.demo.modules.user.model.User;
import micronaut.demo.modules.user.repository.UserRepository;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List <User> findAll() {
        return userRepository.findAll();
    }

}
