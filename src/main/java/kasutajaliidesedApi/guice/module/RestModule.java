package kasutajaliidesedApi.guice.module;

import com.google.inject.servlet.ServletModule;
import kasutajaliidesedApi.guice.GuiceInjector;
import kasutajaliidesedApi.service.AuthenticatedUserService;
import kasutajaliidesedApi.service.JokeService;
import kasutajaliidesedApi.service.UserService;
import kasutajaliidesedApi.service.VoteService;
import org.glassfish.jersey.media.multipart.MultiPartFeature;


@GuiceInjector.Module
public class RestModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(AuthenticatedUserService.class);
        bind(JokeService.class);
        bind(UserService.class);
        bind(VoteService.class);
    }
}
