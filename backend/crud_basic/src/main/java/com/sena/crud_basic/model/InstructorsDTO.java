package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class InstructorsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instructor")
    private int id_instructor;

    @Column(name = "first_name",nullable = false,length = 20)
    private String first_name;

    @Column(name = "last_name",nullable = false,length = 20)
    private String last_name;

    public int getId_instructor() {
        return id_instructor;
    }

    public void setId_instructor(int id_instructor) {
        this.id_instructor = id_instructor;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
