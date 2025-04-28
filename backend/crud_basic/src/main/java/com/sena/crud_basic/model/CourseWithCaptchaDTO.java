package com.sena.crud_basic.model;

public class CourseWithCaptchaDTO {
    private CoursesDTO course;      // Los datos del curso
    private String recaptchaToken;  // El token del captcha
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
