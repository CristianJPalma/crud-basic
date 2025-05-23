package com.sena.crud_basic.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Enrollments")
public class EnrollmentsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enrollment")
    private int id_enrollment;

    @ManyToOne
    @JoinColumn(name = "id_learner")
    private LearnersDTO id_learner;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private CoursesDTO id_course;

    @Column(name = "enrollment_date", nullable = false, length = 10)
    private Date enrollment_date;

    @Column(name = "status", nullable = false, length = 2)
    private int status;

    public EnrollmentsDTO() {
    }

    public EnrollmentsDTO(int id_enrollment, LearnersDTO id_learner, CoursesDTO id_course, Date enrollment_date,
            int status) {
        this.id_enrollment = id_enrollment;
        this.id_learner = id_learner;
        this.id_course = id_course;
        this.enrollment_date = enrollment_date;
        this.status = status;
    }

    public int getId_enrollment() {
        return id_enrollment;
    }

    public void setId_enrollment(int id_enrollment) {
        this.id_enrollment = id_enrollment;
    }

    public LearnersDTO getId_learner() {
        return id_learner;
    }

    public void setId_learner(LearnersDTO id_learner) {
        this.id_learner = id_learner;
    }

    public CoursesDTO getId_course() {
        return id_course;
    }

    public void setId_course(CoursesDTO id_course) {
        this.id_course = id_course;
    }

    public Date getEnrollment_date() {
        return enrollment_date;
    }

    public void setEnrollment_date(Date enrollment_date) {
        this.enrollment_date = enrollment_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
