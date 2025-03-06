package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.InstructorsDTO;

public interface IinstructorsRepository extends JpaRepository<InstructorsDTO, Integer>{

}
