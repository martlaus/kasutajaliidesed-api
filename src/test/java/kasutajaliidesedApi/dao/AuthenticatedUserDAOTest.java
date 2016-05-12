package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.common.test.DatabaseTestBase;
import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.model.User;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by mart on 1.11.15.
 */
public class AuthenticatedUserDAOTest extends DatabaseTestBase {

    @Inject
    private AuthenticatedUserDAO authenticatedUserDAO;

    @Inject
    private UserDAO userDAO;

    @Test
    public void createAuthenticatedUser() throws Exception {
        User user = getUser();

        AuthenticatedUser returnedAuthenticatedUser = createAuthenticatedUser(user, "123123");

        assertEquals(user, returnedAuthenticatedUser.getUser());

        authenticatedUserDAO.delete(returnedAuthenticatedUser);
        userDAO.delete(user);
    }

    @Test
    public void createAuthenticatedUserSameToken() throws Exception {
        User user = getUser();

        AuthenticatedUser returnedAuthenticatedUser = createAuthenticatedUser(user, "123123");

        AuthenticatedUser authenticatedUser2 = new AuthenticatedUser();
        authenticatedUser2.setToken("123123");
        authenticatedUser2.setUser(user);


        try {
            authenticatedUserDAO.createAuthenticatedUser(authenticatedUser2);
            fail("Exception expected");
        } catch (Exception e) {
            //expected
        }

        authenticatedUserDAO.delete(returnedAuthenticatedUser);
        userDAO.delete(user);
    }

    @Test
    public void createAuthenticatedUserTwice() throws Exception {
        User user = getUser();

        AuthenticatedUser authenticatedUser1 = createAuthenticatedUser(user, "token1");

        AuthenticatedUser authenticatedUser2 = createAuthenticatedUser(user, "token2");

        AuthenticatedUser returnedUser1 = authenticatedUserDAO.createAuthenticatedUser(authenticatedUser1);
        AuthenticatedUser returnedUser2 = authenticatedUserDAO.createAuthenticatedUser(authenticatedUser2);

        assertEquals(authenticatedUser1.getUser(),
                authenticatedUserDAO.findAuthenticatedUserByToken("token1").getUser());
        assertEquals(authenticatedUser2.getUser(),
                authenticatedUserDAO.findAuthenticatedUserByToken("token2").getUser());

        authenticatedUserDAO.delete(returnedUser1);
        authenticatedUserDAO.delete(returnedUser2);
        userDAO.delete(user);
    }

    @Test
    public void findAuthenticatedUserByToken() throws Exception {
        User user = getUser();

        AuthenticatedUser returnedAuthenticatedUser = createAuthenticatedUser(user, "123123");

        assertEquals("123123", authenticatedUserDAO.findAuthenticatedUserByToken("123123").getToken());

        authenticatedUserDAO.delete(returnedAuthenticatedUser);
        userDAO.delete(user);
    }

    @Test
    public void delete() throws Exception {
        User user = getUser();
        AuthenticatedUser returnedAuthenticatedUser = createAuthenticatedUser(user, "123123");

        authenticatedUserDAO.delete(returnedAuthenticatedUser);
        assertNull(authenticatedUserDAO.findAuthenticatedUserByToken("123123"));

        userDAO.delete(user);
    }

    private AuthenticatedUser createAuthenticatedUser(User user, String token) throws Exception {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setToken(token);
        authenticatedUser.setUser(user);
        return authenticatedUserDAO.createAuthenticatedUser(authenticatedUser);
    }

    private User getUser() {
        User user = new User();
        user.setCode("someuser@site.ru");
        user.setPassword("superHashedPassword");
        return userDAO.saveUser(user);
    }
}
