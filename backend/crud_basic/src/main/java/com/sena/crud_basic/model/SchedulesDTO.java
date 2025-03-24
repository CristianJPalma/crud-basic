package com.sena.crud_basic.model;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Schedules")
public class SchedulesDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private int id_schedule;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private CoursesDTO id_course;
    
    @ManyToOne
    @JoinColumn(name = "id_instructor")
    private InstructorsDTO id_instructor;

    @Column(name = "week_day", nullable = false, length = 10)
    private String week_day;

    @Column(name = "start_time", nullable = false)
    private Time start_time;

    @Column(name = "end_time", nullable = false)
    private Time end_time;

    @Column(name = "status", nullable = false)
    private int status;

    public SchedulesDTO() {
    }

    public SchedulesDTO(int id_schedule, CoursesDTO id_course, InstructorsDTO id_instructor, String week_day,
            Time start_time, Time end_time, int status) {
        this.id_schedule = id_schedule;
        this.id_course = id_course;
        this.id_instructor = id_instructor;
        this.week_day = week_day;
        this.start_time = start_time;
        this.end_time = end_time;
        this.status = status;
    }

    public int getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(int id_schedule) {
        this.id_schedule = id_schedule;
    }

    public CoursesDTO getId_course() {
        return id_course;
    }

    public void setId_course(CoursesDTO id_course) {
        this.id_course = id_course;
    }

    public InstructorsDTO getId_instructor() {
        return id_instructor;
    }

    public void setId_instructor(InstructorsDTO id_instructor) {
        this.id_instructor = id_instructor;
    }

    public String getWeek_day() {
        return week_day;
    }

    public void setWeek_day(String week_day) {
        this.week_day = week_day;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

    
}
