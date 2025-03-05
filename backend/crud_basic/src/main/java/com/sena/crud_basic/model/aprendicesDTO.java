package com.sena.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * Para indicar que la clase es un modelo, se utiliza
 * la anotación bean @Entity
 * name=El nombre de la entidad en la base datos
 */
@Entity(name = "aprendices")
public class aprendicesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id_aprendiz;

    @Column(name="nombre_aprendiz",nullable = false,length = 50)
    private String nombre_aprendiz;

    @Column(name="apellido_aprendiz",nullable = false, length = 50)
    private String apellido_aprendiz;

    public int getId_aprendiz() {
        return id_aprendiz;
    }

    public void setId_aprendiz(int id_aprendiz) {
        this.id_aprendiz = id_aprendiz;
    }

    public String getNombre_aprendiz() {
        return nombre_aprendiz;
    }

    public void setNombre_aprendiz(String nombre_aprendiz) {
        this.nombre_aprendiz = nombre_aprendiz;
    }

    public String getApellido_aprendiz() {
        return apellido_aprendiz;
    }

    public void setApellido_aprendiz(String apellido_aprendiz) {
        this.apellido_aprendiz = apellido_aprendiz;
    }
    
}
