package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name = "Learners")
public class LearnersDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_learner")
    private int id_learner;

    @Column(name = "first_name",nullable = false,length = 20)
    private String first_name;

    @Column(name = "last_name",nullable = false,length = 20)
    private String last_name;
    
    @Column(name = "status", nullable = false, length =  2)
    private int status;

    public LearnersDTO(int id_learner, String first_name, String last_name, int status) {
        this.id_learner = id_learner;
        this.first_name = first_name;
        this.last_name = last_name;
        this.status = status;
    }

    public int getId_learner() {
        return id_learner;
    }

    public void setId_learner(int id_learner) {
        this.id_learner = id_learner;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
