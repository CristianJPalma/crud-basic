package com.sena.crud_basic.model;

public class LearnerResponseDTO {

    private int id;
    private String fullName;

    public LearnerResponseDTO(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
}
