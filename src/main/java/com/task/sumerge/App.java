package com.task.sumerge;
import com.task.recommender.Course;
import com.task.sumerge.config.Config;
import com.task.sumerge.dto.CourseDTO;
import com.task.sumerge.service.CourseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CourseService courseService = context.getBean(CourseService.class);

        CourseDTO newCourse = new CourseDTO("Python Basics2", "Learn Python", 3);
        courseService.addCourse(newCourse);

        // View a course
        CourseDTO course = courseService.viewCourse(5);
        System.out.println("Course Name: " + course.getName());

        // Update a course
        course.setDescription("Learn Java in depth");
        courseService.updateCourse(course);

    }
}
