package com.sena.crud_basic.model;

public class CourseWithCaptchaDTO {
    private CoursesDTO course;
    private String recaptchaToken;

    // Getters y Setters
    public CoursesDTO getCourse() {
        return course;
    }

    public void setCourse(CoursesDTO course) {
        this.course = course;
    }

    public String getRecaptchaToken() {
        return recaptchaToken;
    }

    public void setRecaptchaToken(String recaptchaToken) {
        this.recaptchaToken = recaptchaToken;
    }
}
