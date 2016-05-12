package kasutajaliidesedApi.rest.filter;

import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.model.User;

import java.security.Principal;

public class KasutajaliidesedApiPrincipal implements Principal {

    private AuthenticatedUser authenticatedUser;

    public KasutajaliidesedApiPrincipal(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public String getName() {
        return getUser().getCode();
    }

    public User getUser() {
        return authenticatedUser.getUser();
    }

    public String getSecurityToken() {
        return authenticatedUser.getToken();
    }

    public boolean isUserInRole(String role) {
        return authenticatedUser != null && authenticatedUser.getUser().getRole().toString().equals(role);
    }

    public AuthenticatedUser getAuthenticatedUser() {
        return authenticatedUser;
    }
}
