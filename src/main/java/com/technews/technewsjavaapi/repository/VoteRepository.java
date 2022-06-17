package com.technews.technewsjavaapi.repository;

import com.technews.technewsjavaapi.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository; // so the interface will inherit the methods used to access the database for standard CRUD operations
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT count(*) FROM Vote v where v.postId = :id")
    int countVotesByPostId(@Param("id") Integer id);
}
