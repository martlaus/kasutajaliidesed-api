package kasutajaliidesedApi.rest.errorhandling;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityTransaction;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static kasutajaliidesedApi.utils.DbUtils.getTransaction;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Throwable error) {
        logger.error("Handling error", error);

        setTransactionRollbackOnly();
        return getResponse(error);
    }

    private Response getResponse(Throwable error) {
        Response response;

        String msg = " - reason unknown.";
        if (error != null) {
            msg = error.getMessage();

        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Message", msg);
        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObject.toString())
                .type(MediaType.APPLICATION_JSON).build();

        return response;
    }

    private void setTransactionRollbackOnly() {
        EntityTransaction transaction = getTransaction();
        if (transaction.isActive()) {
            transaction.setRollbackOnly();
        }
    }
}