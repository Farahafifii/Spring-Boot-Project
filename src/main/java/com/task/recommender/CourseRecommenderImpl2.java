package com.task.recommender;

import java.util.Arrays;
import java.util.List;

public class CourseRecommenderImpl2 implements CourseRecommender {
    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(new Course(), new Course());
    }
}
