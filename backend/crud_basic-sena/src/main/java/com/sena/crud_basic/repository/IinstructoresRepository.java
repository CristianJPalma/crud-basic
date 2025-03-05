package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.instructoresDTO;

public interface IinstructoresRepository extends JpaRepository<instructoresDTO, Integer>{

}
