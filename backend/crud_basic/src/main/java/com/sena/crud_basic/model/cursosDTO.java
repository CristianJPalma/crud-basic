package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity(name = "cursos")
public class cursosDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id_curso;

    @ManyToOne
    @JoinColumn(name="id_instructor")
    private instructoresDTO id_instructor;

    @Column(name = "nombre_curso",nullable= false, length = 50)
    private String nombre_curso;

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public instructoresDTO getId_instructor() {
        return id_instructor;
    }

    public void setId_instructor(instructoresDTO id_instructor) {
        this.id_instructor = id_instructor;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }
    
}
