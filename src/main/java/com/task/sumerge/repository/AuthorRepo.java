package com.task.sumerge.repository;

import com.task.recommender.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<AuthorEntity, Integer> {

    Optional<AuthorEntity> findByEmail(String email);
    Optional<AuthorEntity> findById(int id);
}
