package kasutajaliidesedApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by mart on 10.11.15.
 */
@Entity
public class Vote {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Joke joke;

    @JsonIgnore
    @ManyToOne
    private User user;

    @Column
    private boolean isUpvote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Joke getJoke() {
        return joke;
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
    }

    public boolean isUpvote() {
        return isUpvote;
    }

    public void setIsUpvote(boolean isUpvote) {
        this.isUpvote = isUpvote;
    }
}
