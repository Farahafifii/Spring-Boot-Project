package com.task.sumerge;

import com.task.recommender.CourseRecommenderImpl2;
import com.task.recommender.CourseRecommender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration

public class Config {

    @Bean
    public CourseService courseService( @Qualifier("courseRecommenderImpl2") CourseRecommender courseRecommender) {
        return new CourseService(courseRecommender);
    }
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
