package micronaut.demo.modules.user.service;

import micronaut.demo.comum.exception.NotFoundException;
import micronaut.demo.comum.exception.ValidationException;
import micronaut.demo.modules.user.repository.UserRepository;
import micronaut.demo.modules.user.model.User;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User userNewData) throws NotFoundException {
        Optional <User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userNewData.setId(id);
            if (userRepository.update(userNewData) == 1){
                return this.getUser(id);
            } else {
                throw new ValidationException("Não foi possível atualizar o usuário");
            }
        } else {
            throw new NotFoundException("Usuário não existente");
        }
    }

    public User getUser(Long id) {
        Optional <User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return new User();
    }

    public List <User> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
