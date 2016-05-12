package kasutajaliidesedApi.rest.filter;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static kasutajaliidesedApi.utils.DbUtils.*;

/**
 * Manage database transactions.
 */
@Provider
@Priority(100)
public class TransactionFilter implements ContainerRequestFilter, ContainerResponseFilter {

    /**
     * Starts transaction
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        getTransaction().begin();
    }

    /**
     * Finish transaction. If transaction is marked as rollback only, rollback
     * is performed. Otherwise transaction is committed.
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        closeTransaction();
        closeEntityManager();
    }
}