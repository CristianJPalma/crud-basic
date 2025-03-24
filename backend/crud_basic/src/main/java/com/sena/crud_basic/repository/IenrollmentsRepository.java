package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.EnrollmentsDTO;

public interface IenrollmentsRepository extends JpaRepository<EnrollmentsDTO, Integer>{

    // @Query("SELECT e FROM Enrollments e WHERE c.status = 1")
    // List<EnrollmentsDTO> findAllEnrollmentsActive();

    // @Query("SELECT e FROM Enrollments e WHERE e.enrollment_date LIKE %?1%")
    // List<EnrollmentsDTO> search(String filter);
}
