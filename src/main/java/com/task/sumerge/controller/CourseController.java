package com.task.sumerge.controller;

import com.task.recommender.Course;
import com.task.sumerge.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @PutMapping("/update/{id}")
    public void updateCourse(@PathVariable int id, @RequestBody Course course) {
        course.setId(id);
        courseService.updateCourse(course);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/view/{id}")
    public Course viewCourse(@PathVariable int id) {
        return courseService.viewCourse(id);
    }
    @GetMapping("/discover")
    public List<Course> discoverCourses() {
        return courseService.getRecommendedCourses();
    }

}
