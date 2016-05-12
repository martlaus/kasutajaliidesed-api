package kasutajaliidesedApi.guice.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Guice provider of Entity Manager. It cannot be Singleton otherwise all
 * threads will share the same Entity Manager object and therefore the same
 * transaction.
 */
public class EntityManagerProvider implements Provider<EntityManager> {

    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<EntityManager>();
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Inject
    private EntityManagerFactory emf;

    @Override
    public synchronized EntityManager get() {
        EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
        if (entityManager == null) {
            entityManager = emf.createEntityManager();
            ENTITY_MANAGER_CACHE.set(entityManager);
            logger.debug(String.format("Initializing EntityManager [%s]", entityManager));
        } else if (!entityManager.isOpen()) {
            logger.debug(String.format("EntityManager [%s] is closed, removing from cache.", entityManager));
            ENTITY_MANAGER_CACHE.remove();
            return get();
        }

        return entityManager;
    }
}