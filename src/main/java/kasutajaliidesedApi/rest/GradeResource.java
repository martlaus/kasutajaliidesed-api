package kasutajaliidesedApi.rest;

import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.model.Grade;
import kasutajaliidesedApi.model.User;
import kasutajaliidesedApi.service.GradeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

/**
 * Created by mart on 11.05.16.
 */
@Path("grade")
public class GradeResource {

    @Inject
    private GradeService gradeService;

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Grade getJokeById(@QueryParam("homework") Long homework, @QueryParam("student") Long student) {
//         //gradeService.getGrades(homework, student);
//        return null;
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void update(Grade grade) throws Exception {
        if (grade != null && !Objects.equals(grade.getGrade(), "")) {
           gradeService.changeGrade(grade);
        } else {

            throw new RuntimeException("Unable to change grade");
        }
    }
}
