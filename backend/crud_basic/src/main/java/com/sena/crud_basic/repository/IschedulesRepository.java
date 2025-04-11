package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.sena.crud_basic.model.SchedulesDTO;

public interface IschedulesRepository extends JpaRepository<SchedulesDTO, Integer>{

    @Query("SELECT e FROM Schedules e WHERE e.status = 1")
    List<SchedulesDTO> findAllScheduleActive();

    @Query("SELECT e FROM Schedules e JOIN e.id_instructor l WHERE l.last_name LIKE %?1%")
    List<SchedulesDTO> search(String filter);
}

