package kasutajaliidesedApi.config;

import kasutajaliidesedApi.guice.GuiceInjector;
import kasutajaliidesedApi.guice.provider.ObjectMapperProvider;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;

public class KasutajaliidesedApiApplication extends ResourceConfig {

    @Inject
    public KasutajaliidesedApiApplication(ServiceLocator serviceLocator) {
        // Set package to look for resources in
        packages("kasutajaliidesedApi");

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(GuiceInjector.getInjector());

        register(JacksonFeature.class);
        register(ObjectMapperProvider.class);
        register(RolesAllowedDynamicFeature.class);

    }
}