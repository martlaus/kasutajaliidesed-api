package kasutajaliidesedApi.rest;

import kasutajaliidesedApi.model.AuthenticatedUser;
import kasutajaliidesedApi.model.Student;
import kasutajaliidesedApi.model.User;
import kasutajaliidesedApi.service.GradeService;
import kasutajaliidesedApi.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mart on 25.10.15.
 */
@Path("user")
public class UserResource {

    @Inject
    private UserService userService;

    @Inject
    private GradeService gradeService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticatedUser addUser(User user) throws Exception {
        if (user != null) {
            user = userService.saveUser(user);

            AuthenticatedUser authenticatedUser = userService.getAuthenticatedUser(user);
            if (authenticatedUser != null) {
                return authenticatedUser;
            }

        } else {
            throw new Exception("No user");
        }
        throw new Exception("Unable to register and log in");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("student")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents(@QueryParam("subject") Long subject) {
        List<User> users =  userService.getAllStudents();
        List<Student> students = new ArrayList<>();

        for(User student : users) {

            Student newStudent = new Student();
            newStudent.setId(student.getId());
            newStudent.setName(student.getName());
            newStudent.setGrades(gradeService.getGrades(student, subject));

            students.add(newStudent);
        }

        return students;
    }
}
