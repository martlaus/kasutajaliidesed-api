package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.model.Homework;
import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mart on 30.11.15.
 */
public class HomeworkDAO {

    @Inject
    private EntityManager entityManager;

    public List<Homework> findAll() {
        return entityManager.createQuery("from Homework", Homework.class).getResultList();
    }


    public Homework saveHomework(Homework homework) {

        Homework merged;
        try {
            merged = entityManager.merge(homework);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            throw new RuntimeException("Exception when persisting joke.");
        }

        return merged;
    }

    public List<Homework> getCommentByJokeId(long id) {
        TypedQuery<Homework> findByCode = entityManager
                .createQuery("SELECT u FROM Homework u WHERE u.joke.id = :id", Homework.class);

        List<Homework> homeworks = null;
        try {
            homeworks = findByCode.setParameter("id", id).getResultList();
        } catch (NoResultException ex) {
            // ignore
        }

        return homeworks;
    }

    public void remove(Homework homework) {
        entityManager.remove(homework);
    }


    public Homework getHomeworkById(Long homeworkId) {
        TypedQuery<Homework> findByCode = entityManager
                .createQuery("SELECT u FROM Homework u WHERE u.id = :id", Homework.class);

        Homework homeworks = null;
        try {
            homeworks = findByCode.setParameter("id", homeworkId).getSingleResult();
        } catch (NoResultException ex) {
            // ignore
        }

        return homeworks;
    }

    public List<Homework> getHomeworkByUser(User user) {
        TypedQuery<Homework> findByCode = entityManager
                .createQuery("SELECT u FROM Homework u WHERE u.user = :user", Homework.class);

        List<Homework> homeworks = null;
        try {
            homeworks = findByCode.setParameter("user", user).getResultList();
        } catch (NoResultException ex) {
            // ignore
        }

        return homeworks;
    }
}
