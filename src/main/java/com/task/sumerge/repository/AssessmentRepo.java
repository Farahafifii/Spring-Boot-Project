package com.task.sumerge.repository;

import com.task.recommender.entity.AssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepo extends JpaRepository<AssessmentEntity, Integer> {

    AssessmentEntity findById(int id);
}
