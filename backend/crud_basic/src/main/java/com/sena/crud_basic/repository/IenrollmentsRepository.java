package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.EnrollmentsDTO;

public interface IenrollmentsRepository extends JpaRepository<EnrollmentsDTO, Integer>{

}
