package com.task.sumerge;
import java.util.List;
import com.task.recommender.CourseRecommender;
import com.task.recommender.Course;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseRecommenderImpl implements CourseRecommender {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> recommendedCourses() {
        return courseRepository.discoverCourses();
    }
}
