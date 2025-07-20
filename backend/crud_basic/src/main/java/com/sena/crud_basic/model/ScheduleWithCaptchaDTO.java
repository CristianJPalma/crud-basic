package com.sena.crud_basic.model;

public class ScheduleWithCaptchaDTO {
    private SchedulesDTO schedule;
    private String recaptchaToken;

    public SchedulesDTO getSchedule() { return schedule; }
    public void setSchedule(SchedulesDTO schedule) { this.schedule = schedule; }
    public String getRecaptchaToken() { return recaptchaToken; }
    public void setRecaptchaToken(String recaptchaToken) { this.recaptchaToken = recaptchaToken; }
}
