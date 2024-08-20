package com.task.sumerge.repository;

import com.task.recommender.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, Integer> {

    List<CourseEntity> findById(String name);

    List<CourseEntity> findByNameContaining(String keyword);
}
