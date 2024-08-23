package com.task.sumerge;

import java.util.List;
import com.task.recommender.CourseRecommender;
import com.task.recommender.entity.CourseEntity;
import com.task.sumerge.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseRecommenderImpl implements CourseRecommender {

    @Autowired
    private CourseRepo courseRepository;

    @Override
    public List<CourseEntity> recommendedCourses() {
        return courseRepository.findAll();
    }
}
