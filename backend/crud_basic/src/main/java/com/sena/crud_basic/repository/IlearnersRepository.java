package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.LearnersDTO;

public interface IlearnersRepository extends JpaRepository<LearnersDTO, Integer>{

}
