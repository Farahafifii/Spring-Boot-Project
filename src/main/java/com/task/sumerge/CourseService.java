package com.task.sumerge;

import org.springframework.beans.factory.annotation.Autowired;
import com.task.recommender.CourseRecommender;
import com.task.recommender.Course;
import java.util.*;


public class CourseService {
    CourseRecommender courseRecommender;
    @Autowired
    private CourseRecommender courseRecommenderImpl; // By variable name

    @Autowired
    public CourseService( CourseRecommender courseRecommender) { // using qualifier
        this.courseRecommender = courseRecommender;
    }

    public List<Course> getRecommendedCourses() {
        return courseRecommender.recommendedCourses();
    }

    public List<Course> getRecommendedCourses2() {
        return courseRecommenderImpl.recommendedCourses();
    }
}
