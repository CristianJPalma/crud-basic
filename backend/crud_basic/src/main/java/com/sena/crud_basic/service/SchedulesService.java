package com.sena.crud_basic.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;

import com.sena.crud_basic.model.SchedulesDTO;
import com.sena.crud_basic.model.ScheduleWithCaptchaDTO;

import com.sena.crud_basic.repository.IschedulesRepository;

@Service
public class SchedulesService {

    @Autowired
    private IschedulesRepository IschedulesRepository;

    @Autowired
    private CaptchaService captchaService;

    public List<SchedulesDTO> getAllSchedules() {
        return IschedulesRepository.findAllScheduleActive();
    }

    public List<SchedulesDTO> getFilterSchedule(String filter) {
        return IschedulesRepository.search(filter);
    }

    public SchedulesDTO getScheduleById(int id) {
        return IschedulesRepository.findById(id).orElse(null);
    }

    public responseDTO save(ScheduleWithCaptchaDTO scheduleWithCaptchaDTO) {
        boolean isCaptchaValid = captchaService.validateCaptcha(scheduleWithCaptchaDTO.getRecaptchaToken());
        if (!isCaptchaValid) {
            return new responseDTO("Error", "Captcha inválido. La operación ha sido rechazada.");
        }
        SchedulesDTO schedule = scheduleWithCaptchaDTO.getSchedule();
        if (schedule.getId_instructor() == null) {
            return new responseDTO("Error", "El instructor es obligatorio.");
        }
        if (schedule.getId_course() == null) {
            return new responseDTO("Error", "El curso es obligatorio.");
        }
        if (schedule.getWeek_day() == null || schedule.getWeek_day().trim().isEmpty()) {
            return new responseDTO("Error", "El día de la semana es obligatorio.");
        }
        if (schedule.getStart_time() == null || schedule.getEnd_time() == null) {
            return new responseDTO("Error", "La hora de inicio y fin son obligatorias.");
        }
        if (schedule.getStart_time().after(schedule.getEnd_time())) {
            return new responseDTO("Error", "La hora de inicio no puede ser después de la hora de fin.");
        }
        IschedulesRepository.save(schedule);
        return new responseDTO("OK", "Se registró correctamente");
    }

    public responseDTO delete(ScheduleWithCaptchaDTO scheduleWithCaptchaDTO) {
        boolean isCaptchaValid = captchaService.validateCaptcha(scheduleWithCaptchaDTO.getRecaptchaToken());
        if (!isCaptchaValid) {
            return new responseDTO("Error", "Captcha inválido. La operación ha sido rechazada.");
        }
        int id = scheduleWithCaptchaDTO.getSchedule().getId_schedule();
        SchedulesDTO schedule = getScheduleById(id);
        if (schedule == null) {
            return new responseDTO("Error", "El horario no existe.");
        }
        schedule.setStatus(0);
        IschedulesRepository.save(schedule);
        return new responseDTO("OK", "Se eliminó correctamente");
    }
}
