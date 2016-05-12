package kasutajaliidesedApi.service;

import kasutajaliidesedApi.dao.AuthenticatedUserDAO;
import kasutajaliidesedApi.model.AuthenticatedUser;

import javax.inject.Inject;

/**
 * Created by mart on 8.11.15.
 */
public class AuthenticatedUserService {

    @Inject
    private AuthenticatedUserDAO authenticatedUserDAO;

    public AuthenticatedUser getAuthenticatedUserByToken(String token) {
        return authenticatedUserDAO.findAuthenticatedUserByToken(token);
    }

    public void deleteAuthenticatedUser(AuthenticatedUser authenticatedUser) {
        authenticatedUserDAO.delete(authenticatedUser);
    }
}
