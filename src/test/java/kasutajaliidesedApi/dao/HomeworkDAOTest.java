package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.common.test.DatabaseTestBase;
import kasutajaliidesedApi.model.Homework;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 30.11.15.
 */
public class HomeworkDAOTest extends DatabaseTestBase{

    @Inject
    private HomeworkDAO homeworkDAO;

    @Test
    public void saveComment(){
        Homework homework = new Homework();
        Homework returnedHomework = homeworkDAO.saveHomework(homework);

        assertNotNull(returnedHomework);
        assertNotNull(returnedHomework.getId());

        homeworkDAO.remove(returnedHomework);
    }

    @Test
    public void getAll(){
        List<Homework> homeworks = homeworkDAO.findAll();

        assertEquals(2, homeworks.size());
    }

    @Test
    public void getCommentByJokeId() {
        List<Homework> homeworks = homeworkDAO.getCommentByJokeId(1);
        assertNotNull(homeworks);
        assertEquals(2, homeworks.size());
    }
}
