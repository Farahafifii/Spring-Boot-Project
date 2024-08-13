package com.task.sumerge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service //@Component

public class CourseService {
    CourseRecommender courseRecommender;
    @Autowired
    private CourseRecommender courseRecommenderImpl; // By variable name

    @Autowired
    public CourseService( @Qualifier("courseRecommenderImpl2") CourseRecommender courseRecommender) { // using qualifier
        this.courseRecommender = courseRecommender;
    }

    public List<Course> getRecommendedCourses() {
        return courseRecommender.recommendedCourses();
    }

    public List<Course> getRecommendedCourses2() {
        return courseRecommenderImpl.recommendedCourses();
    }
}
