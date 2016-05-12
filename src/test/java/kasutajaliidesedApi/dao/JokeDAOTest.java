package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.common.test.DatabaseTestBase;
import kasutajaliidesedApi.model.Joke;
import org.joda.time.DateTime;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mart on 28.09.15.
 */
public class JokeDAOTest extends DatabaseTestBase {

    @Inject
    private JokeDAO jokeDAO;

    @Test
    public void findAll() {
        List<Joke> jokes = jokeDAO.findAll();

        //assertEquals(2, jokes.size());
        assertValidJoke(jokes.get(0));
        assertValidJoke(jokes.get(1));
    }

    @Test
    public void getById() {
        Joke joke = jokeDAO.getJokeById(1L);

        assertValidJoke(joke);
    }

    @Test
    public void saveJoke() {
        int sizeBefore = jokeDAO.findAll().size();
        Joke joke = new Joke();
        joke.setAdded(new DateTime(6666));
        joke.setJoke("A blind man walks down the street...");
        Joke savedJoke = jokeDAO.saveJoke(joke);

        assertEquals(sizeBefore + 1, jokeDAO.findAll().size());
        assertEquals("A blind man walks down the street...", jokeDAO.findAll().get(2).getJoke());
        jokeDAO.remove(savedJoke);

        assertEquals(sizeBefore, jokeDAO.findAll().size());

    }

    @Test
    public void findAllJokesFrom() {
        List<Joke> jokes = jokeDAO.findAllJokesFrom(new DateTime("1990-06-30T01:20"));

        //assertEquals(2, jokes.size());
        assertValidJoke(jokes.get(0));
        assertValidJoke(jokes.get(1));
    }

    private void assertValidJoke(Joke joke) {
        assertNotNull(joke.getId());
        assertNotNull(joke.getJoke());
        if (joke.getId() == 1) {
            assertEquals("yo moma so fat", joke.getJoke());
        } else if (joke.getId() == 2) {
            assertEquals("yo papa so fat", joke.getJoke());
        } else {
            fail("Joke with unexpected id.");
        }
    }
}
