package com.technews.repository;

import com.technews.model.User;
import org.springframework.data.jpa.repository.JpaRepository; // so the interface will inherit the methods used to access the database for standard CRUD operations
import org.springframework.stereotype.Repository;

@Repository // any class that fulfills the role of a data access object (DAO)—
           // in other words, it contains data retrieval, storage, and search functionality

// <User, Integer> to ensure that the repository can take User (from the entity model we created) and the id of that user
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email) throws Exception; // custom query method
}
