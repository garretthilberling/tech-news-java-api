package com.technews.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity // marks this as a persistable object, so that the User class can map to a table

@JsonIgnoreProperties // specifies properties that should be ignored when serializing this object to JSON.
                     // The two arguments that follow the annotation are the properties to be ignored.
                    // Note that you could add many more if necessary

@Table(name = "user") // specifies the name of the table that this class maps to.
                     // If this annotation isn't present, the table name will be the class name by default

public class User {
    @Id // signals that id will be used as the unique identifier
    @GeneratedValue(strategy = GenerationType.AUTO) // denotes that it will be a generated value
    private Integer id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @Transient // signals to Spring Data JPA that this data is NOT to be persisted in the database
    boolean loggedIn;

    // EAGER, meaning that this list will gather all of its necessary information immediately after being created
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;
     // variables designated as LAZY only gather information as they need it.
    // Need to use FetchType.LAZY to resolve multiple bags exception
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.technews.model.Vote> votes;
    // Need to use FetchType.LAZY to resolve multiple bags exception
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    public User(Integer id, String username, String email, String password, boolean loggedIn, List<Post> posts, List<com.technews.model.Vote> votes, List<Comment> comments) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.loggedIn = loggedIn;
        this.posts = posts;
        this.votes = votes;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<com.technews.model.Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<com.technews.model.Vote> votes) {
        this.votes = votes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isLoggedIn() == user.isLoggedIn() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getPosts(), user.getPosts()) &&
                Objects.equals(getVotes(), user.getVotes()) &&
                Objects.equals(getComments(), user.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), isLoggedIn(), getPosts(), getVotes(), getComments());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                ", posts=" + posts +
                ", votes=" + votes +
                ", comments=" + comments +
                '}';
    }
}
