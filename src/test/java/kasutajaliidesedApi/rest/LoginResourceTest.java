package kasutajaliidesedApi.rest;

import kasutajaliidesedApi.common.test.ResourceIntegrationTestBase;
import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.model.User;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 8.11.15.
 */
public class LoginResourceTest extends ResourceIntegrationTestBase {

    @Test
    public void logIn() {
        User user = new User();
        user.setCode("mart@mart.kz");
        user.setPassword("mart");

        Response response = doPost("signin", Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        AuthenticatedUser authenticatedUser = response.readEntity(new GenericType<AuthenticatedUser>() {
        });
        assertNotNull(authenticatedUser.getToken());
        assertNotNull(authenticatedUser.getId());


    }

    @Test
    public void loginWrongId() {
        Response response = doPost("signin", Entity.entity(new User(), MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    @Test
    public void loginNullId() {
        Response response = doPost("signin", Entity.entity(null, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());

    }
}
