package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Courses")
public class CoursesDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_courses")
    private int id_courses;

    @Column(name = "course_name", nullable = false, length = 20)
    private String course_name;

    public int getId_courses() {
        return id_courses;
    }

    public void setId_courses(int id_courses) {
        this.id_courses = id_courses;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
    
}
