package kasutajaliidesedApi.guice.module;

import com.google.inject.AbstractModule;
import kasutajaliidesedApi.ApplicationLauncher;
import kasutajaliidesedApi.ApplicationManager;
import kasutajaliidesedApi.guice.GuiceInjector;

@GuiceInjector.Module
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        requestStaticInjection(ApplicationLauncher.class);
        requestStaticInjection(ApplicationManager.class);
    }
}
