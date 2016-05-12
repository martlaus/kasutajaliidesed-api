package kasutajaliidesedApi.service;

import kasutajaliidesedApi.dao.GradeDAO;
import kasutajaliidesedApi.dao.HomeworkDAO;
import kasutajaliidesedApi.dao.UserDAO;
import kasutajaliidesedApi.model.Grade;
import kasutajaliidesedApi.model.User;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by mart on 11.05.16.
 */
public class GradeService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private HomeworkDAO homeworkDAO;

    @Inject
    private GradeDAO gradeDAO;

    public List<Grade> getGrades( User student, Long subject) {

        return gradeDAO.getGradeByStudentAndSubject(student, subject);
    }

    public Grade changeGrade(Grade grade) {
        Grade old = gradeDAO.getGradeById(grade.getId());
        old.setGrade(grade.getGrade());
        return gradeDAO.update(old);
    }
}
