package kasutajaliidesedApi.service;

import kasutajaliidesedApi.dao.HomeworkDAO;
import kasutajaliidesedApi.model.Homework;
import kasutajaliidesedApi.model.User;
import kasutajaliidesedApi.rest.filter.KasutajaliidesedApiPrincipal;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by mart on 30.11.15.
 */
public class HomeworkService {

    @Inject
    private HomeworkDAO homeworkDAO;

    public Homework addHomework(Homework homework, SecurityContext securityContext) {
        KasutajaliidesedApiPrincipal kasutajaliidesedApiPrincipal = (KasutajaliidesedApiPrincipal) securityContext.getUserPrincipal();
        homework.setUser(kasutajaliidesedApiPrincipal.getUser());

        return homeworkDAO.saveHomework(homework);
    }

    public List<Homework> getAllHomework() {
        return homeworkDAO.findAll();
    }

    public List<Homework> getUsersHomework(SecurityContext securityContext) {
        KasutajaliidesedApiPrincipal kasutajaliidesedApiPrincipal = (KasutajaliidesedApiPrincipal) securityContext.getUserPrincipal();

        User user = kasutajaliidesedApiPrincipal.getUser();
        return homeworkDAO.getHomeworkByUser(user);
    }
}
