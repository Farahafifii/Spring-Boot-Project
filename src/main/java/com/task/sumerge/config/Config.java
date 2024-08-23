package com.task.sumerge.config;

import com.task.recommender.CourseRecommender;
import com.task.sumerge.CourseRecommenderImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.task.sumerge")
@EntityScan(basePackages = "com.task.recommender.entity")
public class Config {

    @Bean
    @Primary
    public CourseRecommender courseRecommenderImpl() {
        return new CourseRecommenderImpl();
    }

}
