package com.task.sumerge;

import com.task.recommender.CourseRecommenderImpl2;
import com.task.recommender.CourseRecommender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.task.sumerge")
public class Config {

    @Bean
    @Primary
    public CourseRecommender courseRecommenderImpl() {
        return new CourseRecommenderImpl();
    }

    @Bean
    public CourseRecommender courseRecommenderImpl2() {
        return  new CourseRecommenderImpl2();
    }

}
