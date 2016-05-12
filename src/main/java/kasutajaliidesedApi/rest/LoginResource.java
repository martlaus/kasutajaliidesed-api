package kasutajaliidesedApi.rest;

import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.model.User;
import kasutajaliidesedApi.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * Created by mart on 1.11.15.
 */
@Path("signin")
public class LoginResource {

    @Inject
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticatedUser login(User user) throws Exception {
        if (user != null) {
            AuthenticatedUser authenticatedUser = userService.logIn(user);
            if (authenticatedUser != null) {
                return authenticatedUser;
            }
        }
        throw new RuntimeException("Unable to log in - wrong user info or the user is not registered");
    }
}
