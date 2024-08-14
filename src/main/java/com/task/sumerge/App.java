package com.task.sumerge;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CourseService courseService = context.getBean(CourseService.class);

        Course newCourse = new Course("Python Basics", "Learn Java", 3);
        courseService.addCourse(newCourse);

        // View a course
        Course course = courseService.viewCourse(2);
        System.out.println("Course Name: " + course.getName());

        // Update a course
        course.setDescription("Learn Java in depth");
        courseService.updateCourse(course);

    }
}
