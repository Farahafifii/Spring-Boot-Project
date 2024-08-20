package com.task.recommender;


public class Course {
    private int id;
    private String name;
    private String description;
    private int credit;


    public Course() {}

    public Course( String name, String description, int credit) {
        this.name = name;
        this.description = description;
        this.credit = credit;
    }

    // Getters and setters
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
