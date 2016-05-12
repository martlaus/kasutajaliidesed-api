package kasutajaliidesedApi.rest.filter;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;

public class KasutajaliidesedApiSecurityContext implements SecurityContext {

    private UriInfo uriInfo;
    private KasutajaliidesedApiPrincipal kasutajaliidesedApiPrincipal;

    public KasutajaliidesedApiSecurityContext(KasutajaliidesedApiPrincipal principal, UriInfo uriInfo) {
        this.uriInfo = uriInfo;
        this.kasutajaliidesedApiPrincipal = principal;
    }

    @Override
    public Principal getUserPrincipal() {
        return kasutajaliidesedApiPrincipal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return kasutajaliidesedApiPrincipal != null && kasutajaliidesedApiPrincipal.isUserInRole(role);
    }

    @Override
    public boolean isSecure() {
        return "https".equals(uriInfo.getRequestUri().getScheme());
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.CLIENT_CERT_AUTH;
    }
}
