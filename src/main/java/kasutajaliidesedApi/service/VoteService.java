package kasutajaliidesedApi.service;

import kasutajaliidesedApi.dao.JokeDAO;
import kasutajaliidesedApi.dao.VoteDAO;
import kasutajaliidesedApi.model.Joke;
import kasutajaliidesedApi.model.Vote;
import kasutajaliidesedApi.rest.filter.KasutajaliidesedApiPrincipal;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by mart on 11.11.15.
 */
public class VoteService {

    @Inject
    private VoteDAO voteDAO;

    @Inject
    private JokeDAO jokeDAO;

    public void upVote(Vote vote, SecurityContext securityContext) throws Exception {
        vote.setId(null);
        vote.setIsUpvote(true);

        //set user
        KasutajaliidesedApiPrincipal kasutajaliidesedApiPrincipal = (KasutajaliidesedApiPrincipal) securityContext.getUserPrincipal();
        vote.setUser(kasutajaliidesedApiPrincipal.getUser());

        //check if duplicate
        List<Vote> votes = voteDAO.getVotesByJokeAndUser(vote.getJoke(), kasutajaliidesedApiPrincipal.getUser());
        if(votes.size() > 0 && votes.get(0).isUpvote()) {
            throw new Exception("Duplicate upvote");
        } else if (votes.size() > 0 && !votes.get(0).isUpvote()) {
            //delete downvote when adding upvote
            voteDAO.remove(votes.get(0));
        }

        //set joke
        Joke joke = jokeDAO.getJokeById(vote.getJoke().getId());
        vote.setJoke(joke);

        voteDAO.saveVote(vote);
    }

    public void downVote(Vote vote, SecurityContext securityContext) throws Exception {
        vote.setId(null);
        vote.setIsUpvote(false);

        //set user
        KasutajaliidesedApiPrincipal kasutajaliidesedApiPrincipal = (KasutajaliidesedApiPrincipal) securityContext.getUserPrincipal();
        vote.setUser(kasutajaliidesedApiPrincipal.getUser());

        //check if duplicate
        List<Vote> votes = voteDAO.getVotesByJokeAndUser(vote.getJoke(), kasutajaliidesedApiPrincipal.getUser());
        if(votes.size() > 0 && !votes.get(0).isUpvote()) {
            throw new Exception("Duplicate downvote");
        } else if (votes.size() > 0 && votes.get(0).isUpvote()) {
            //delete upvote when downvoteing
            voteDAO.remove(votes.get(0));
        }

        //set joke
        Joke joke = jokeDAO.getJokeById(vote.getJoke().getId());
        vote.setJoke(joke);

        voteDAO.saveVote(vote);
    }
}
