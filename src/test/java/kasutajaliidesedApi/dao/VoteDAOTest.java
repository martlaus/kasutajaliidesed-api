package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.common.test.DatabaseTestBase;
import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.model.User;
import kasutajaliidesedApi.model.Vote;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 10.11.15.
 */
public class VoteDAOTest extends DatabaseTestBase {

    @Inject
    private VoteDAO voteDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private JokeDAO jokeDAO;

    @Test
    public void findAll() {
        List<Vote> votes = voteDAO.findAll();

        assertNotNull(votes.get(0));
    }

    @Test
    public void findByUserAndJoke() {
        Joke joke = jokeDAO.getJokeById(1);
        User user = userDAO.getUserByEmail("admin@admin.kz");

        List<Vote> votes = voteDAO.getVotesByJokeAndUser(joke, user);

        assertNotNull(votes.get(0));
        assertEquals(1, votes.get(0).getId().intValue());
    }

    @Test
    public void saveVote() {
        Vote vote = new Vote();
        Joke joke = new Joke();
        joke.setId(1L);
        vote.setJoke(joke);
        User user = new User();
        user.setId(1L);
        vote.setUser(user);
        vote.setIsUpvote(true);

        //int initialSize = voteDAO.findAll().size();

        voteDAO.saveVote(vote);

        // assertEquals(initialSize + 1, userDAO.findAll().size());
    }

    @Test
    public void getVotesByJokeAndUser() {
        Joke joke = new Joke();
        User user = new User();
        joke.setId(1L);
        user.setId(1L);

        List<Vote> votes = voteDAO.getVotesByJokeAndUser(joke, user);
        assertNotNull(votes);
    }
}
