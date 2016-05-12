package kasutajaliidesedApi.guice.provider;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertSame;

@RunWith(EasyMockRunner.class)
public class EntityManagerProviderTest {

    @TestSubject
    private EntityManagerProvider entityManagerProvider = new EntityManagerProvider();

    @Mock
    private EntityManagerFactory emf;

    @Test
    public void get() {
        EntityManager entityManager1 = EasyMock.createMock(EntityManager.class);
        EntityManager entityManager2 = EasyMock.createMock(EntityManager.class);
        expect(emf.createEntityManager()).andReturn(entityManager1);
        expect(emf.createEntityManager()).andReturn(entityManager2);

        int repeat = 5;
        expect(entityManager1.isOpen()).andReturn(true).times(repeat);
        expect(entityManager1.isOpen()).andReturn(false);

        // This is needed to remove mock from cache when other tests are running
        expect(entityManager2.isOpen()).andReturn(false).anyTimes();

        replay(emf, entityManager1, entityManager2);

        for (int i = 0; i <= repeat; i++) {
            assertSame(entityManager1, entityManagerProvider.get());
        }

        assertSame(entityManager2, entityManagerProvider.get());

        verify(emf, entityManager1, entityManager2);
    }
}
