package com.sena.crud_basic.model;


public class CaptchaDTO {
    
    private String recaptchaToken;  // El token del captcha

    public String getRecaptchaToken() {
        return recaptchaToken;
    }

    public void setRecaptchaToken(String recaptchaToken) {
        this.recaptchaToken = recaptchaToken;
    }
}
