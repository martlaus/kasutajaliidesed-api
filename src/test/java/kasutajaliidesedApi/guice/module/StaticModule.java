package kasutajaliidesedApi.guice.module;

import com.google.inject.AbstractModule;
import kasutajaliidesedApi.guice.GuiceInjector.Module;
import kasutajaliidesedApi.server.EmbeddedJettyTest;

@Module
public class StaticModule extends AbstractModule {

    @Override
    protected void configure() {
        requestStaticInjection(EmbeddedJettyTest.class);
    }
}
