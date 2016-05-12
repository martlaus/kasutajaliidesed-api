package kasutajaliidesedApi.rest.filter;

import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.service.AuthenticatedUserService;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static kasutajaliidesedApi.guice.GuiceInjector.getInjector;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    private static final int HTTP_AUTHENTICATION_TIMEOUT = 419;

    private UriInfo uriInfo;
    private HttpServletRequest request;

    public SecurityFilter(@Context UriInfo uriInfo, @Context HttpServletRequest request) {
        this.uriInfo = uriInfo;
        this.request = request;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = request.getHeader("Token");

        if (token != null) {
            AuthenticatedUserService authenticatedUserService = newAuthenticatedUserService();
            AuthenticatedUser authenticatedUser = authenticatedUserService.getAuthenticatedUserByToken(token);
            if (authenticatedUser != null && isCorrectUser(authenticatedUser)) {
                KasutajaliidesedApiPrincipal principal = new KasutajaliidesedApiPrincipal(authenticatedUser);
                KasutajaliidesedApiSecurityContext securityContext = new KasutajaliidesedApiSecurityContext(principal, uriInfo);
                requestContext.setSecurityContext(securityContext);
            } else {
                Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Denied")
                        .type(MediaType.APPLICATION_JSON).build();
            }
        }

    }

    protected AuthenticatedUserService newAuthenticatedUserService() {
        return getInjector().getInstance(AuthenticatedUserService.class);
    }

    private boolean isCorrectUser(AuthenticatedUser authenticatedUser) {
        return authenticatedUser.getUser().getCode().equals(request.getHeader("Code"));
    }

}
