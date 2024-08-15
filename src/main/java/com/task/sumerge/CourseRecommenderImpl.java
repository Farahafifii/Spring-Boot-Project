package com.task.sumerge;
import java.util.Arrays;
import java.util.List;
import com.task.recommender.CourseRecommender;
import com.task.recommender.Course;


public class CourseRecommenderImpl implements CourseRecommender {
    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(new Course(), new Course());
    }
}
