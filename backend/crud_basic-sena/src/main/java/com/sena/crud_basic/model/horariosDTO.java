package com.sena.crud_basic.model;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "horarios")
public class horariosDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id_horario;

    @OneToOne
    @JoinColumn(name = "id_curso")
    private cursosDTO id_curso;

    @Column(name = "dia_semana", nullable= false, length = 20)
    private String dia_semana;

    @Column(name ="horario_inicio", nullable = false)
    private Time horario_inicio;
    
    @Column(name = "horario_fin",nullable = false)
    private Time horario_fin;

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public cursosDTO getId_curso() {
        return id_curso;
    }

    public void setId_curso(cursosDTO id_curso) {
        this.id_curso = id_curso;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public Time getHorario_inicio() {
        return horario_inicio;
    }

    public void setHorario_inicio(Time horario_inicio) {
        this.horario_inicio = horario_inicio;
    }

    public Time getHorario_fin() {
        return horario_fin;
    }

    public void setHorario_fin(Time horario_fin) {
        this.horario_fin = horario_fin;
    }
    
}
