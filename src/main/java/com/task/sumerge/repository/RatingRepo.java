package com.task.sumerge.repository;

import com.task.recommender.entity.CourseEntity;
import com.task.recommender.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepo extends JpaRepository<RatingEntity, Integer> {

    RatingEntity findByCourse(CourseEntity course);
}
