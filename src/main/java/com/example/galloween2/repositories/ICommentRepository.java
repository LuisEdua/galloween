package com.example.galloween2.repositories;

import com.example.galloween2.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "DELETE FROM comments WHERE user_id = :userId ;", nativeQuery = true)
    void deleteByUserId(Long userId);

}
