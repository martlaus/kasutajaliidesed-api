package kasutajaliidesedApi.common.test;

import kasutajaliidesedApi.guice.GuiceInjector;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import static kasutajaliidesedApi.guice.GuiceInjector.getInjector;

/**
 * JUnit test runner that initialize Guice.
 *
 */
public class GuiceTestRunner extends BlockJUnit4ClassRunner {

    public GuiceTestRunner(Class<?> klass) throws InitializationError {
        super(klass);

        GuiceInjector.init();
    }

    @Override
    public Object createTest() throws Exception {
        Object test = super.createTest();
        getInjector().injectMembers(test);
        return test;
    }
}
