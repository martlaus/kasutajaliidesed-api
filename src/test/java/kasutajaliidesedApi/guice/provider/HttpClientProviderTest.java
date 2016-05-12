package kasutajaliidesedApi.guice.provider;

import com.google.inject.Injector;
import kasutajaliidesedApi.common.test.GuiceTestRunner;
import kasutajaliidesedApi.guice.GuiceInjector;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Configuration;

import static org.junit.Assert.*;

@RunWith(GuiceTestRunner.class)
public class HttpClientProviderTest {

    @Test
    public void get() {
        Client client = GuiceInjector.getInjector().getInstance(Client.class);

        assertNotNull(client);
        Configuration configuration = client.getConfiguration();
        assertTrue(configuration.isRegistered(JacksonFeature.class));
        assertTrue(configuration.isRegistered(ObjectMapperProvider.class));
    }

    @Test
    public void getAlwaysReturnSameObject() {
        Injector injector = GuiceInjector.getInjector();
        Client client = injector.getInstance(Client.class);

        for (int i = 0; i < 10; i++) {
            assertSame(client, injector.getInstance(Client.class));
        }
    }
}
