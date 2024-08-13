package com.task.sumerge;

import com.task.sumerge.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CourseService courseService = context.getBean(CourseService.class);
        System.out.println(courseService.getRecommendedCourses());
        System.out.println(courseService.getRecommendedCourses2());
    }
}
