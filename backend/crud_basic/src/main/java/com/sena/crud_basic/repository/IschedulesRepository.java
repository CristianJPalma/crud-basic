package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.SchedulesDTO;

public interface IschedulesRepository extends JpaRepository<SchedulesDTO, Integer>{

}
