package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.CoursesDTO;

public interface IcoursesRepository extends JpaRepository<CoursesDTO, Integer> {

    @Query("SELECT c FROM courses c WHERE c.status = 1")
    List<CoursesDTO> findAllCoursesActive();

    @Query("SELECT c FROM courses c WHERE c.course_name LIKE %?1%")
    List<CoursesDTO> search(String filter);
}
