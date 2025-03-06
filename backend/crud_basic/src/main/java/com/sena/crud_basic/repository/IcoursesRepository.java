package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.CoursesDTO;

public interface IcoursesRepository extends JpaRepository<CoursesDTO, Integer> {

}
