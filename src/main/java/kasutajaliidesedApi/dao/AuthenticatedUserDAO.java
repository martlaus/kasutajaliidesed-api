package kasutajaliidesedApi.dao;

import kasutajaliidesedApi.model.AuthenticatedUser;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Created by mart on 1.11.15.
 */
public class AuthenticatedUserDAO {

    @Inject
    private EntityManager entityManager;

    public AuthenticatedUser createAuthenticatedUser(AuthenticatedUser authenticatedUser) throws Exception {
        AuthenticatedUser merged;
        try {
            merged = entityManager.merge(authenticatedUser);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            throw new RuntimeException("Error when persisting authenticatedUser.");
        }

        return merged;
    }

    public AuthenticatedUser findAuthenticatedUserByToken(String token) {
        TypedQuery<AuthenticatedUser> findByToken = entityManager
                .createQuery("SELECT a FROM AuthenticatedUser a WHERE a.token = :token",
                        AuthenticatedUser.class);

        AuthenticatedUser user = null;
        try {
            user = findByToken.setParameter("token", token).getSingleResult();
        } catch (Exception e) {
            // ignore
        }

        return user;
    }

    public void delete(AuthenticatedUser authenticatedUser) {
        AuthenticatedUser merged = entityManager.merge(authenticatedUser);
        entityManager.remove(merged);
    }
}
