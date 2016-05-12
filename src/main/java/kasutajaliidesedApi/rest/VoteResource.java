package kasutajaliidesedApi.rest;

import kasutajaliidesedApi.model.Vote;
import kasutajaliidesedApi.service.VoteService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by mart on 11.11.15.
 */
@Path("vote")
public class VoteResource {

    @Inject
    private VoteService voteService;

    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @POST
    @RolesAllowed("USER")
    @Path("upvote")
    @Produces(MediaType.APPLICATION_JSON)
    public void upVote(Vote vote) throws Exception {
        if (vote != null) {
            voteService.upVote(vote, securityContext);
        } else {
            throw new Exception("No vote.");
        }
    }

    @POST
    @RolesAllowed("USER")
    @Path("downvote")
    @Produces(MediaType.APPLICATION_JSON)
    public void downVote(Vote vote) throws Exception {
        if (vote != null) {
            voteService.downVote(vote, securityContext);
        } else {
            throw new Exception("No vote.");
        }

    }
}
