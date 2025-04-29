package com.sena.crud_basic.model;

public class InstructorWithCaptchaDTO {
    private InstructorsDTO instructor;
    private String recaptchaToken;
    
    public InstructorsDTO getInstructor() {
        return instructor;
    }
    public void setInstructor(InstructorsDTO instructor) {
        this.instructor = instructor;
    }
    public String getRecaptchaToken() {
        return recaptchaToken;
    }
    public void setRecaptchaToken(String recaptchaToken) {
        this.recaptchaToken = recaptchaToken;
    }

}
