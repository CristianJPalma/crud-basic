package com.sena.crud_basic.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity(name = "inscripciones")
public class inscripcionesDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id_inscripcion;

    @OneToMany
    @JoinColumn(name = "id_aprendiz")
    private aprendicesDTO id_aprendiz;


    @JoinColumn(name = "id_curso")
    private cursosDTO id_curso;

 
    @JoinColumn(name = "id_instructor")
    private instructoresDTO id_instructor;

    @Column(name = "fecha_inscripcion")
    private Date fecha_inscripcion;

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public aprendicesDTO getId_aprendiz() {
        return id_aprendiz;
    }

    public void setId_aprendiz(aprendicesDTO id_aprendiz) {
        this.id_aprendiz = id_aprendiz;
    }

    public cursosDTO getId_curso() {
        return id_curso;
    }

    public void setId_curso(cursosDTO id_curso) {
        this.id_curso = id_curso;
    }

    public instructoresDTO getId_instructor() {
        return id_instructor;
    }

    public void setId_instructor(instructoresDTO id_instructor) {
        this.id_instructor = id_instructor;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }
    
}
