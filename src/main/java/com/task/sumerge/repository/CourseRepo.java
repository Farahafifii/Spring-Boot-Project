package com.task.sumerge.repository;

import com.task.recommender.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, Integer> {


    Optional<CourseEntity> findById(int id);
    List<CourseEntity> findByNameContaining(String keyword);
}
