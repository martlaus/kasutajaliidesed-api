package kasutajaliidesedApi.service;

import kasutajaliidesedApi.dao.HomeworkDAO;
import kasutajaliidesedApi.dao.JokeDAO;
import kasutajaliidesedApi.model.Homework;
import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.model.User;
import kasutajaliidesedApi.rest.filter.KasutajaliidesedApiPrincipal;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.SecurityContext;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 9.12.15.
 */
@RunWith(EasyMockRunner.class)
public class HomeworkServiceTest {

    @TestSubject
    private HomeworkService homeworkService = new HomeworkService();

    @Mock
    private HomeworkDAO homeworkDAO;

    @Mock
    private JokeDAO jokeDAO;

    @Test
    public void addComment() {
        Homework homework = new Homework();
        Joke joke = new Joke();
        joke.setId(1l);

        SecurityContext securityContext = createMock(SecurityContext.class);
        KasutajaliidesedApiPrincipal kasutajaliidesedApiPrincipal = createMock(KasutajaliidesedApiPrincipal.class);

        expect(securityContext.getUserPrincipal()).andReturn(kasutajaliidesedApiPrincipal);
        expect(kasutajaliidesedApiPrincipal.getUser()).andReturn(new User());
        expect(jokeDAO.getJokeById(joke.getId())).andReturn(joke);
        expect(homeworkDAO.saveHomework(homework)).andReturn(homework);

        replay(homeworkDAO, jokeDAO, securityContext, kasutajaliidesedApiPrincipal);

        Homework returnedHomework = homeworkService.addHomework(homework, securityContext);

        assertNotNull(returnedHomework);

        verify(homeworkDAO, jokeDAO, securityContext, kasutajaliidesedApiPrincipal);
    }
}
