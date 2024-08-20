package com.task.sumerge.dto;

import java.time.LocalDate;

public class AuthorDTO {
    private int id;

    private String name;
    private String email;
    private LocalDate birthdate;

    public AuthorDTO() {}

    public AuthorDTO(int id, String name, String email, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
