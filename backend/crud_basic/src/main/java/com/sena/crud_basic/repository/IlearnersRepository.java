package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.LearnersDTO;

public interface IlearnersRepository extends JpaRepository<LearnersDTO, Integer>{
    @Query("SELECT e FROM Learners e WHERE e.status = 1")
    List<LearnersDTO> findAllLearnersActive();

    @Query("SELECT e FROM Learners e WHERE e.first_name LIKE %?1%")
    List<LearnersDTO> search(String filter);
}
