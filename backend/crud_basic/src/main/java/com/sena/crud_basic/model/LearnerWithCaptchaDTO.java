package com.sena.crud_basic.model;

public class LearnerWithCaptchaDTO {

    private LearnersDTO learner;
    private String recaptchaToken;
    public LearnersDTO getLearner() {
        return learner;
    }
    public void setLearner(LearnersDTO learner) {
        this.learner = learner;
    }
    public String getRecaptchaToken() {
        return recaptchaToken;
    }
    public void setRecaptchaToken(String recaptchaToken) {
        this.recaptchaToken = recaptchaToken;
    }

    
}
