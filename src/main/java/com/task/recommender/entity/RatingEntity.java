package com.task.recommender.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    public RatingEntity() {}

    public RatingEntity(int number, CourseEntity course) {
        this.number = number;
        this.course = course;
    }

}
