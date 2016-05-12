package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mart on 25.10.15.
 */
public class UserDAO {

    @Inject
    private EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User saveUser(User user) {

        User merged;
        try {
            merged = entityManager.merge(user);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception when persisting user.");
        }

        return merged;
    }

    public User getUserByEmail(String email) {
        TypedQuery<User> findByCode = entityManager
                .createQuery("SELECT u FROM User u WHERE u.code = :email", User.class);

        User user = null;
        try {
            user = findByCode.setParameter("email", email).getSingleResult();
        } catch (NoResultException ex) {
            // ignore
        }

        return user;
    }

    public void delete(User user) {
        entityManager.remove(user);
    }

    public List<User> findAllStudents() {

        TypedQuery<User> findByCode = entityManager
                .createQuery("SELECT u FROM User u WHERE u.role = :role", User.class);

        List<User> users = null;
        try {
            users = findByCode.setParameter("role", "USER").getResultList();
        } catch (NoResultException ex) {
            // ignore
        }

        return users;
    }

    public User getUserById(Long student) {
        TypedQuery<User> findByCode = entityManager
                .createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);

        User user = null;
        try {
            user = findByCode.setParameter("id", student).getSingleResult();
        } catch (NoResultException ex) {
            // ignore
        }

        return user;
    }
}
