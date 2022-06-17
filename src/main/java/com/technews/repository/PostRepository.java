package com.technews.repository;

import com.technews.model.Post;
import org.springframework.data.jpa.repository.JpaRepository; // so the interface will inherit the methods used to access the database for standard CRUD operations
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // any class that fulfills the role of a data access object (DAO)—
           // in other words, it contains data retrieval, storage, and search functionality

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllPostsByUserId(Integer id) throws Exception;
}
