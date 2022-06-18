package com.technews.controller;

import com.technews.model.Post;
import com.technews.model.User;
import com.technews.repository.UserRepository;
import com.technews.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // allows processing of JSON/XML responses and sending JSON/XML objects via the API
public class UserController {
    @Autowired // tells Spring to scan the project for objects that will need to be instantiated for a class or method to run
    UserRepository repository; // unlike the new operator in JS, which instantiates all objects before they're necessarily needed,
                              // @Autowired informs Spring to only instantiate each object as needed by the program
    @Autowired
    VoteRepository voteRepository;

    // get all users
    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        List<User> userList = repository.findAll();
        for (User u : userList) {
            List<Post> postList = u.getPosts(); // posts for every User, assigned to the variable u inside userList
            for (Post p : postList) {
                p.setVoteCount(voteRepository.countVotesByPostId(p.getId())); // set vote count for each Post, assigned to the variable p inside postList
            }
        }
        return userList;
    }

    // get single user by id
    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        User returnUser = repository.getReferenceById(id);
        List<Post> postList = returnUser.getPosts();
        for (Post p : postList) {
            p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
        }
        return returnUser;
    }

    // create user
    @PostMapping("/api/users")
    public User addUser(@RequestBody User user) { // @RequestBody annotation—which will map the body of this request to a transfer object, then deserialize the body onto a Java object for easier use
        // Hash password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        repository.save(user);
        return user;
    }

    // update user
    @PutMapping("/api/users/{id}")
    public User updatedUser(@PathVariable int id, @RequestBody User user) { // @PathVariable will allow us to enter the int id into the request URI as a parameter
        User tempUser = repository.getReferenceById(id);

        if (!tempUser.equals(null)) {
            user.setId(tempUser.getId());
            repository.save(user);
        }
        return user;
    }

    // remove user
    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }
}
