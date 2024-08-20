package com.task.sumerge.controller;

import com.task.sumerge.dto.CourseDTO;
import com.task.sumerge.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public void addCourse(@RequestBody CourseDTO course) {
        courseService.addCourse(course);
    }

    @PutMapping("/update/{id}")
    public void updateCourse(@PathVariable int id, @RequestBody CourseDTO course) {
        course.setId(id);
        courseService.updateCourse(course);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/view/{id}")
    public CourseDTO viewCourse(@PathVariable int id) {
        return courseService.viewCourse(id);
    }

    @GetMapping("/discover")
    public List<CourseDTO> discoverCourses() {
        return courseService.discoverCourses("Python");
    }

    @GetMapping("/findAll")
    public List<CourseDTO> findAllCourses(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return courseService.findAllCourses(page, size);
    }

}
