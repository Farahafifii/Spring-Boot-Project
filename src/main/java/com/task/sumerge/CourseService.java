package com.task.sumerge;

import com.task.recommender.CourseRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.task.recommender.Course;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRecommender courseRecommender;

    @Autowired
    private CourseRepository courseRepository;

    public CourseService( ) {
    }

    public CourseService( CourseRecommender courseRecommender) { // using qualifier
        this.courseRecommender = courseRecommender;
    }

    public void addCourse(Course course) {
        courseRepository.addCourse(course);
    }

    public Course viewCourse(int id) {
       return courseRepository.viewCourse(id);
    }

    // Method to update an existing course
    public void updateCourse(Course course) {
        courseRepository.updateCourse(course);
    }

    // Method to delete a course by id
    public void deleteCourse(int id) {
       courseRepository.deleteCourse(id);
    }

    public List<Course> getRecommendedCourses() {
        return courseRecommender.recommendedCourses();
    }
}
