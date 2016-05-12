package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.common.test.DatabaseTestBase;
import kasutajaliidesedApi.model.User;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mart on 25.10.15.
 */
public class UserDAOTest extends DatabaseTestBase {

    @Inject
    private UserDAO userDAO;

    @Test
    public void findAll() {
        List<User> users = userDAO.findAll();

        assertValidUser(users.get(0));
        assertValidUser(users.get(1));
    }

    @Test
    public void getUserByEmail(){
        User user = userDAO.getUserByEmail("admin@admin.kz");

        assertNotNull(user.getId());
        assertEquals("admin@admin.kz", user.getCode());
        assertNotNull(user.getPassword());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setCode("kazaa@lankara.ru");
        user.setPassword("parool");

        int initialSize = userDAO.findAll().size();

        userDAO.saveUser(user);

        assertEquals(initialSize + 1, userDAO.findAll().size());
    }

    private void assertValidUser(User user) {
        assertNotNull(user.getId());
        assertNotNull(user.getCode());
        assertNotNull(user.getPassword());
        if (user.getId() == 1) {
            assertEquals("admin@admin.kz", user.getCode());
        } else if (user.getId() == 2) {
            assertEquals("user@user.kz", user.getCode());
        } else {
            fail("User with unexpected id.");
        }
    }
}
