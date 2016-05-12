package kasutajaliidesedApi.rest;

import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.service.JokeService;
import kasutajaliidesedApi.utils.DateUtils;
import org.joda.time.DateTime;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by mart on 28.09.15.
 */
@Path("joke")
public class JokeResource {
    @Inject
    private JokeService jokeService;

    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Joke> getAllJokes(@QueryParam("startingDate") String startingDate) throws Exception {
        if (startingDate == null) {
            return jokeService.getAllJokes();
        } else {
            DateTime dateTime = DateUtils.fromJson(startingDate);

        }

        throw new Exception("Something went wrong");
    }

    @POST
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Joke addJoke(Joke joke) throws Exception {
        if (joke != null) {
            return jokeService.saveJoke(joke, securityContext);
        }

        throw new Exception("unable to persist joke.");
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Joke getJokeById(@PathParam("id") Long id) {
        return jokeService.getJokeById(id);
    }

    @DELETE
    @RolesAllowed("USER")
    @Path("{id}")
    public void deleteJoke(@PathParam("id") Long id) throws IllegalAccessException {
        jokeService.delete(id, securityContext);
    }
}
