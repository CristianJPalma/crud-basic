package com.sena.crud_basic.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.SchedulesDTO;
import com.sena.crud_basic.repository.IschedulesRepository;

@Service
public class SchedulesService {

    @Autowired
        private IschedulesRepository ischedulesRepository;

    public boolean save(SchedulesDTO schedule){
        ischedulesRepository.save(schedule);
        return true;
    }
}
