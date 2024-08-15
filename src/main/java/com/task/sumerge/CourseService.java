package com.task.sumerge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.task.recommender.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

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



}
