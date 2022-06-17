package com.technews.technewsjavaapi.repository;

import com.technews.technewsjavaapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository; // so the interface will inherit the methods used to access the database for standard CRUD operations
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // any class that fulfills the role of a data access object (DAO)—
           // in other words, it contains data retrieval, storage, and search functionality

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllCommentsByPostId(int postId);
}
