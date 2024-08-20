package com.task.recommender.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private int credit;

    @ManyToMany(mappedBy = "courses")
    private Set<AuthorEntity> authors = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingEntity> ratings;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assessment_id", referencedColumnName = "id")
    private AssessmentEntity assessment =  new AssessmentEntity();

    public CourseEntity() {}

    public CourseEntity(String name, String description, int credit) {
        this.name = name;
        this.description = description;
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
