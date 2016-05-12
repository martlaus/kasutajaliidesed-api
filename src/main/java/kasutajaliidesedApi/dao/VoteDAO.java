package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.model.User;
import kasutajaliidesedApi.model.Vote;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mart on 10.11.15.
 */
public class VoteDAO {

    @Inject
    private EntityManager entityManager;

    public List<Vote> findAll() {
        return entityManager.createQuery("from Vote", Vote.class).getResultList();
    }

    public Vote saveVote(Vote vote) {

        Vote merged;
        try {
            merged = entityManager.merge(vote);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            throw new RuntimeException("Exception when persisting vote.");
        }

        return merged;
    }

    public List<Vote> getVotesByJokeAndUser(Joke joke, User user) {
        TypedQuery<Vote> findByCode = entityManager
                .createQuery("SELECT u FROM Vote u WHERE u.joke = :jokeId and u.user = :userId", Vote.class);

        List<Vote> votes = null;
        try {
            votes = findByCode.setParameter("jokeId", joke).setParameter("userId", user).getResultList();
        } catch (NoResultException ex) {
            // ignore
        }

        return votes;
    }

    public List<Vote> getUpVotesByJoke(Joke joke) {
        TypedQuery<Vote> findByCode = entityManager
                .createQuery("SELECT u FROM Vote u WHERE u.joke = :jokeId and u.isUpvote = true", Vote.class);

        List<Vote> votes = null;
        try {
            votes = findByCode.setParameter("jokeId", joke).getResultList();
        } catch (NoResultException ex) {
            // ignore
        }

        return votes;
    }

    public List<Vote> getDownVotesByJoke(Joke joke) {
        TypedQuery<Vote> findByCode = entityManager
                .createQuery("SELECT u FROM Vote u WHERE u.joke = :jokeId and u.isUpvote = false", Vote.class);

        List<Vote> votes = null;
        try {
            votes = findByCode.setParameter("jokeId", joke).getResultList();
        } catch (NoResultException ex) {
            // ignore
        }

        return votes;
    }

    public void remove(Vote vote) {
        entityManager.remove(vote);
    }

}
