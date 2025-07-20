package com.sena.crud_basic.model;

public class EnrollmentWithCaptchaDTO {
    private EnrollmentsDTO enrollment;
    private String recaptchaToken;

    public EnrollmentsDTO getEnrollment() { return enrollment; }
    public void setEnrollment(EnrollmentsDTO enrollment) { this.enrollment = enrollment; }
    public String getRecaptchaToken() { return recaptchaToken; }
    public void setRecaptchaToken(String recaptchaToken) { this.recaptchaToken = recaptchaToken; }
}
