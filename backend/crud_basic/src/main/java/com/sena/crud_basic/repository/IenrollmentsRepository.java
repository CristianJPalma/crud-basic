package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.EnrollmentsDTO;

public interface IenrollmentsRepository extends JpaRepository<EnrollmentsDTO, Integer>{

    @Query("SELECT e FROM Enrollments e WHERE e.status = 1")
    List<EnrollmentsDTO> findAllEnrollmentsActive();

    //@Query("SELECT e FROM Enrollments e JOIN Learners l ON e.id_learner = l.id_learner WHERE l.last_name LIKE %?1%")
    @Query("SELECT e FROM Enrollments e JOIN e.id_learner l WHERE l.last_name LIKE %?1%")
    List<EnrollmentsDTO> search(String filter);
}
