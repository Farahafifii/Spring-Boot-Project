package com.task.sumerge.repository;

import com.task.recommender.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<AuthorEntity, Integer> {

    AuthorEntity findByEmail(String email);
    AuthorEntity findById(int id);
}
