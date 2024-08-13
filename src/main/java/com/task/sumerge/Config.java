package com.task.sumerge;

import com.task.sumerge.CourseRecommender;
import com.task.sumerge.CourseRecommenderImpl;
import com.task.sumerge.CourseRecommenderImpl2;
import com.task.sumerge.CourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.task.sumerge")
public class Config {

    @Bean
    @Primary
    public CourseRecommender courseRecommenderImpl() {
        return new CourseRecommenderImpl();
    }

    @Bean
    public CourseRecommender courseRecommenderImpl2() {
        return new CourseRecommenderImpl2();
    }
}
