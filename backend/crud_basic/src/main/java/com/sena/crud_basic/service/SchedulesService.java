package com.sena.crud_basic.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;

import com.sena.crud_basic.model.SchedulesDTO;

import com.sena.crud_basic.repository.IschedulesRepository;

@Service
public class SchedulesService {

 @Autowired
    private IschedulesRepository IschedulesRepository;

    public List<SchedulesDTO> getAllSchedules() {
        return IschedulesRepository.findAllScheduleActive();
    }

    public List<SchedulesDTO> getFilterSchedule(String filter) {
        return IschedulesRepository.search(filter);
    }

    public SchedulesDTO getScheduleById(int id) {
        return IschedulesRepository.findById(id).get();
    }

    public responseDTO save(SchedulesDTO schedule) {
        if (schedule.getId_instructor()==null) {
            responseDTO response = new responseDTO(
                    "Error",
                    "El nombre debe tener una longitud entre 1 y 255 caracteres");
            return response;
        }

        // añadir las n condiciones

        IschedulesRepository.save(schedule);
        responseDTO response = new responseDTO(
                "OK",
                "Se registró correctamente");
        return response;
        // return true;
    }


    public responseDTO delete(int id) {
        SchedulesDTO schedule = getScheduleById(id);
        schedule.setStatus(0);
        IschedulesRepository.save(schedule);
        responseDTO response = new responseDTO(
                "OK",
                "Se eliminó correctamente");
        return response;
    }
}
