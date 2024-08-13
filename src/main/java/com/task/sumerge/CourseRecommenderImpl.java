package com.task.sumerge;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;


public class CourseRecommenderImpl implements CourseRecommender {
    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(new Course("Python Basic"), new Course("Python Advanced"));
    }
}
