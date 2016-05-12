package kasutajaliidesedApi.rest;

import kasutajaliidesedApi.common.test.ResourceIntegrationTestBase;
import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.model.User;
import kasutajaliidesedApi.model.Vote;
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
 * Created by mart on 11.11.15.
 */
public class VoteResourceTest extends ResourceIntegrationTestBase {

    @Test
    public void upVote() {
        User user = new User();
        user.setCode("mart@mart.kz");
        user.setPassword("mart");

        Response response = doPost("signin", Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        AuthenticatedUser authenticatedUser = response.readEntity(new GenericType<AuthenticatedUser>() {
        });

        Response response2 = doGet("joke/1");

        Joke originalJoke = response2.readEntity(new GenericType<Joke>() {
        });

        assertNotNull(authenticatedUser.getToken());
        String token = authenticatedUser.getToken();

        Vote vote = new Vote();
        Joke joke = new Joke();
        joke.setId(1L);
        vote.setJoke(joke);

        Response response3 = getTarget("vote/upvote", new LoggedInUserFilter(token)).request().accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(vote, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response3.getStatus());

        Response response4 = doGet("joke/1");

        Joke returnedJoke = response4.readEntity(new GenericType<Joke>() {
        });

        assertTrue(originalJoke.getUpvotes() < returnedJoke.getUpvotes());
    }

    @Test
    public void downVote() {
        User user = new User();
        user.setCode("mart@mart.kz");
        user.setPassword("mart");

        Response response = doPost("signin", Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        AuthenticatedUser authenticatedUser = response.readEntity(new GenericType<AuthenticatedUser>() {
        });

        Response response2 = doGet("joke/2");

        Joke originalJoke = response2.readEntity(new GenericType<Joke>() {
        });

        assertNotNull(authenticatedUser.getToken());
        String token = authenticatedUser.getToken();

        Vote vote = new Vote();
        Joke joke = new Joke();
        joke.setId(2L);
        vote.setJoke(joke);

        Response response3 = getTarget("vote/downvote", new LoggedInUserFilter(token)).request().accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(vote, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response3.getStatus());

        Response response4 = doGet("joke/2");

        Joke returnedJoke = response4.readEntity(new GenericType<Joke>() {
        });

        assertEquals(originalJoke.getDownvotes() + 1, returnedJoke.getDownvotes());
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
