package micronaut.demo.modules.user.repository;

import io.micronaut.spring.tx.annotation.Transactional;
import micronaut.demo.modules.user.model.User;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List <User> findAll() {
        String qlString = "SELECT u FROM User as u";
        TypedQuery <User> query = entityManager.createQuery(qlString, User.class);
        return query.getResultList();
    }

}
