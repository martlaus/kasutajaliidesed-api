package kasutajaliidesedApi.guice.module;

import com.google.inject.AbstractModule;
import kasutajaliidesedApi.guice.GuiceInjector.Module;
import kasutajaliidesedApi.guice.provider.ConfigurationProvider;
import kasutajaliidesedApi.guice.provider.EntityManagerFactoryProvider;
import kasutajaliidesedApi.guice.provider.EntityManagerProvider;
import kasutajaliidesedApi.guice.provider.HttpClientProvider;
import org.apache.commons.configuration.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.client.Client;

@Module
public class ProviderModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Configuration.class).toProvider(ConfigurationProvider.class);
        bind(EntityManagerFactory.class).toProvider(EntityManagerFactoryProvider.class);
        bind(EntityManager.class).toProvider(EntityManagerProvider.class);
        bind(Client.class).toProvider(HttpClientProvider.class);
    }
}
