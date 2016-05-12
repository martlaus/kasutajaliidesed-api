package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.model.Joke;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mart on 28.09.15.
 */
public class JokeDAO {

    @Inject
    private EntityManager entityManager;

    public List<Joke> findAll() {
        return entityManager.createQuery("from Joke", Joke.class).getResultList();
    }

    public Joke saveJoke(Joke joke) {

        Joke merged;
        try {
            merged = entityManager.merge(joke);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            throw new RuntimeException("Exception when persisting joke.");
        }

        return merged;
    }

    public Joke getJokeById(long id) {
        TypedQuery<Joke> findByCode = entityManager
                .createQuery("SELECT u FROM Joke u WHERE u.id = :id", Joke.class);

        Joke joke = null;
        try {
            joke = findByCode.setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            // ignore
        }

        return joke;
    }

    public void remove(Joke joke) {
        entityManager.remove(joke);
    }

    public List<Joke> findAllJokesFrom(DateTime dateTime) {
        TypedQuery<Joke> findByCode = entityManager
                .createQuery("SELECT j FROM Joke j WHERE j.added >= :date", Joke.class);

        List<Joke> jokes = null;
        try {
            jokes = findByCode.setParameter("date", dateTime).getResultList();
        } catch (NoResultException ex) {
            // ignore
        }
        return jokes;
    }

}
