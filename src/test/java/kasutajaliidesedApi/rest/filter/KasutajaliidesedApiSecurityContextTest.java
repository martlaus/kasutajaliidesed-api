package kasutajaliidesedApi.rest.filter;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(EasyMockRunner.class)
public class KasutajaliidesedApiSecurityContextTest {

    @Mock
    private KasutajaliidesedApiPrincipal kasutajaliidesedApiPrincipal;

    @Mock
    private UriInfo uriInfo;

    @Test
    public void isUserInRoleNull() {
        KasutajaliidesedApiSecurityContext securityContext = getKasutajaliidesedApiSecurityContext(null, uriInfo);

        boolean response = securityContext.isUserInRole("USER");

        assertFalse(response);
    }

    @Test
    public void isUserInRole() {
        KasutajaliidesedApiSecurityContext securityContext = getKasutajaliidesedApiSecurityContext(kasutajaliidesedApiPrincipal, uriInfo);

        expect(kasutajaliidesedApiPrincipal.isUserInRole("USER")).andReturn(true);

        replay(kasutajaliidesedApiPrincipal, uriInfo);

        assertTrue(securityContext.isUserInRole("USER"));

        verify(kasutajaliidesedApiPrincipal, uriInfo);

    }

    @Test
    public void isSecureFalse() throws URISyntaxException {
        KasutajaliidesedApiSecurityContext securityContext = getKasutajaliidesedApiSecurityContext(kasutajaliidesedApiPrincipal, uriInfo);
        URI uri = new URI("http://random.org");

        expect(uriInfo.getRequestUri()).andReturn(uri);

        replay(kasutajaliidesedApiPrincipal, uriInfo);

        assertFalse(securityContext.isSecure());

        verify(kasutajaliidesedApiPrincipal, uriInfo);
    }

    @Test
    public void isSecure() throws URISyntaxException {
        KasutajaliidesedApiSecurityContext securityContext = getKasutajaliidesedApiSecurityContext(kasutajaliidesedApiPrincipal, uriInfo);
        URI uri = new URI("https://random.org");

        expect(uriInfo.getRequestUri()).andReturn(uri);

        replay(kasutajaliidesedApiPrincipal, uriInfo);

        assertTrue(securityContext.isSecure());

        verify(kasutajaliidesedApiPrincipal, uriInfo);
    }

    private KasutajaliidesedApiSecurityContext getKasutajaliidesedApiSecurityContext(KasutajaliidesedApiPrincipal kasutajaliidesedApiPrincipal, UriInfo uriInfo) {
        return new KasutajaliidesedApiSecurityContext(kasutajaliidesedApiPrincipal, uriInfo);
    }
}
