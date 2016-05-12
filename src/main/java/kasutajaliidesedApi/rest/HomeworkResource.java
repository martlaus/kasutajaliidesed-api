package kasutajaliidesedApi.rest;

import kasutajaliidesedApi.model.Homework;
import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.rest.filter.KasutajaliidesedApiPrincipal;
import kasutajaliidesedApi.service.HomeworkService;
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
 * Created by mart on 30.11.15.
 */
@Path("homework")
public class HomeworkResource {

    @Inject
    private HomeworkService homeworkService;

    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @GET
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Homework> getUsersHomeworks() throws Exception {
        return homeworkService.getUsersHomework(securityContext);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Homework> getAllHomeworks() throws Exception {
        return homeworkService.getAllHomework();

    }

    @POST
    @RolesAllowed({"USER", "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public Homework addHomework(Homework homework) throws Exception {
        if (homework != null) {
            return homeworkService.addHomework(homework, securityContext);
        } else {
            throw new Exception("No homework.");
        }
    }

}
