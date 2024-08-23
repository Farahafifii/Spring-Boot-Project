package com.task.recommender;

import com.task.recommender.entity.CourseEntity;

import java.util.Arrays;
import java.util.List;

public class CourseRecommenderImpl2 implements CourseRecommender {
    @Override
    public List<CourseEntity> recommendedCourses() {
        return Arrays.asList(new CourseEntity("hello from other class", "DESCRIPTION",3), new CourseEntity("hello from other class2", "DESCRIPTION2",3));
    }
}
