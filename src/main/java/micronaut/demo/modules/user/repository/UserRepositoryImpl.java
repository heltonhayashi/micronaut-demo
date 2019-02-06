package micronaut.demo.modules.user.repository;

import io.micronaut.spring.tx.annotation.Transactional;
import micronaut.demo.modules.user.model.User;
import micronaut.demo.modules.user.repository.UserRepository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional <User> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public User save(@NotBlank User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(user -> entityManager.remove(user));
    }

    @Override
    @Transactional
    public List <User> findAll() {
        String qlString = "SELECT u FROM User as u";
        TypedQuery <User> query = entityManager.createQuery(qlString, User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public int update(User user) {
        return entityManager.createQuery("UPDATE User e SET name = :name, email = :email, password = :password, birthdate = :birthdate where id = :id")
                .setParameter("id", user.getId())
                .setParameter("name", user.getName())
                .setParameter("email", user.getEmail())
                .setParameter("password", user.getPassword())
                .setParameter("birthdate", user.getBirthdate())

                .executeUpdate();
    }
}
