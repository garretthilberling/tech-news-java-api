package com.technews.controller;

import com.technews.model.Comment;
import com.technews.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentRepository repository;
    // get all comments
    @GetMapping("/api/comments")
    public List<Comment> getAllComments() {
        return repository.findAll();
    }

    // get single comment by id
    @GetMapping("/api/comments/{id}")
    public Comment getComment(@PathVariable int id) {
        return repository.getById(id);
    }

    // create a comment
    @PostMapping("/api/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment) {
        return repository.save(comment);
    }

    // update a comment
    @PutMapping("/api/updateComment")
    public Comment updateComment(@RequestBody Comment comment) {
        return repository.save(comment);
    }

    // delete a comment by id
    @DeleteMapping("/api/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable int id) {
        repository.deleteById(id);
    }
}