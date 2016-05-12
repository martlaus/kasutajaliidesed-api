package kasutajaliidesedApi.rest;

import kasutajaliidesedApi.common.test.ResourceIntegrationTestBase;
import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.model.Homework;
import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.model.User;
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

import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 9.12.15.
 */
public class HomeworkResourceTest extends ResourceIntegrationTestBase {

    @Test
    public void comment() {
        User user = new User();
        user.setCode("mart@mart.kz");
        user.setPassword("mart");

        Response response = doPost("signin", Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        AuthenticatedUser authenticatedUser = response.readEntity(new GenericType<AuthenticatedUser>() {
        });

        assertNotNull(authenticatedUser.getToken());
        String token = authenticatedUser.getToken();

        Joke joke = new Joke();
        joke.setId(666l);
        Homework homework = new Homework();
        homework.setUser(user);

        Response response2 = getTarget("homework", new LoggedInUserFilter(token)).request().accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(homework, MediaType.APPLICATION_JSON_TYPE));

        Homework returnedHomework = response2.readEntity(new GenericType<Homework>() {
        });

        assertNotNull(returnedHomework);
        assertNotNull(returnedHomework.getId());
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
