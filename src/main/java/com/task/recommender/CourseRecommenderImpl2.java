package com.task.recommender;

import java.util.Arrays;
import java.util.List;

public class CourseRecommenderImpl2 implements CourseRecommender {
    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(new Course("hello from other class", "DESCRIPTION",3), new Course("hello from other class2", "DESCRIPTION2",3));
    }
}
