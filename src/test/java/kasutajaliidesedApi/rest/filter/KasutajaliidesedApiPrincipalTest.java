package kasutajaliidesedApi.rest.filter;

import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.model.User;
import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(EasyMockRunner.class)
public class KasutajaliidesedApiPrincipalTest {

    AuthenticatedUser authenticatedUser = getUser();

    @TestSubject
    private KasutajaliidesedApiPrincipal kasutajaliidesedApiPrincipal = new KasutajaliidesedApiPrincipal(authenticatedUser);

    @Test
    public void getName() {
        assertEquals("admin@admin.kz", kasutajaliidesedApiPrincipal.getName());
    }

    @Test
    public void getToken() {
        assertNull(kasutajaliidesedApiPrincipal.getSecurityToken());
    }

    @Test
    public void isUserInRole() {
        assertTrue(kasutajaliidesedApiPrincipal.isUserInRole("USER"));
    }

    private AuthenticatedUser getUser() {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        User user = new User();
        user.setCode("admin@admin.kz");
        user.setRole("USER");
        authenticatedUser.setUser(user);

        return authenticatedUser;
    }
}
