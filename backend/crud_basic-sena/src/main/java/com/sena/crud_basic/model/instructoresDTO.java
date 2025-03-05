package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "instructores")
public class instructoresDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id_instructor;

    @Column(name = "nombre_instructor", nullable = false, length = 50)
    private String nombre_instructor;

    @Column(name = "apellido_instructor", nullable = false, length = 50)
    private String apellido_instructor;

    public int getId_instructor() {
        return id_instructor;
    }

    public void setId_instructor(int id_instructor) {
        this.id_instructor = id_instructor;
    }

    public String getNombre_instructor() {
        return nombre_instructor;
    }

    public void setNombre_instructor(String nombre_instructor) {
        this.nombre_instructor = nombre_instructor;
    }

    public String getApellido_instructor() {
        return apellido_instructor;
    }

    public void setApellido_instructor(String apellido_instructor) {
        this.apellido_instructor = apellido_instructor;
    }
}
