package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.sena.crud_basic.model.InstructorsDTO;

public interface IinstructorsRepository extends JpaRepository<InstructorsDTO, Integer>{

    @Query("SELECT c FROM Instructors c WHERE c.status = 1")
    List<InstructorsDTO> findAllInstructorsActive();

    @Query("SELECT c FROM Instructors c WHERE c.first_name LIKE %?1%")
    List<InstructorsDTO> search(String filter);
}
