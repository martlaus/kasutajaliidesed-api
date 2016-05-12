package kasutajaliidesedApi.rest;

import kasutajaliidesedApi.common.test.ResourceIntegrationTestBase;
import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.model.User;
import org.joda.time.DateTime;
import org.junit.Test;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mart on 28.09.15.
 */
public class JokeResourceTest extends ResourceIntegrationTestBase {

    @Test
    public void getAllJokes() {
        Response response = doGet("joke");

        List<Joke> jokes = response.readEntity(new GenericType<List<Joke>>() {
        });

        assertValidJoke(jokes.get(0));
        assertValidJoke(jokes.get(1));

    }

    @Test
    public void getJokeById() {
        Response response = doGet("joke/1");

        Joke joke = response.readEntity(new GenericType<Joke>() {
        });

        assertValidJoke(joke);
    }

    @Test
    public void addJokeAndDelete() {
        User user = new User();
        user.setCode("mart@mart.kz");
        user.setPassword("mart");

        Response response = doPost("signin", Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        AuthenticatedUser authenticatedUser = response.readEntity(new GenericType<AuthenticatedUser>() {
        });

        assertNotNull(authenticatedUser.getToken());
        String token = authenticatedUser.getToken();

        Response jokes = doGet("joke");

        int size = jokes.readEntity(new GenericType<List<Joke>>() {
        }).size();

        Joke jokeBefore = new Joke();
        jokeBefore.setAdded(new DateTime(6666));
        jokeBefore.setJoke("A man in a wheelchair walks down the street...");

        Response response2 = getTarget("joke", new LoggedInUserFilter(token)).request().accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(jokeBefore, MediaType.APPLICATION_JSON_TYPE));

        Joke returnedJoke = response2.readEntity(new GenericType<Joke>() {
        });
        jokes = doGet("joke");
        List<Joke> jokes2 = jokes.readEntity(new GenericType<List<Joke>>() {
        });

        assertEquals(200, response2.getStatus());
        assertEquals(size + 1, jokes2.size());

        Response response3 = getTarget("joke/" + returnedJoke.getId(), new LoggedInUserFilter(token)).request().accept(MediaType.APPLICATION_JSON_TYPE)
                .delete();

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response3.getStatus());

        jokes = doGet("joke");
        List<Joke> jokes3 = jokes.readEntity(new GenericType<List<Joke>>() {
        });

        assertEquals(size, jokes3.size());

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

    @Provider
    public static class LoggedInUserFilter implements ClientRequestFilter {
        String token = null;

        public LoggedInUserFilter(String token) {
            this.token = token;
        }

        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
            List<Object> list3 = new ArrayList<>();
            list3.add(token);
            requestContext.getHeaders().put("Token", list3);

            List<Object> list4 = new ArrayList<>();
            list4.add("mart@mart.kz");
            requestContext.getHeaders().put("Email", list4);
        }
    }
}
