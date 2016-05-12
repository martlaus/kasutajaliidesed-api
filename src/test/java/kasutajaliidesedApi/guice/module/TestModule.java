package kasutajaliidesedApi.guice.module;

import com.google.inject.AbstractModule;
import kasutajaliidesedApi.common.test.ResourceIntegrationTestBase;
import kasutajaliidesedApi.guice.GuiceInjector.Module;

@Module()
public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        requestStaticInjection(ResourceIntegrationTestBase.class);
    }
}
