package com.sena.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;

import com.sena.crud_basic.model.SchedulesDTO;
import com.sena.crud_basic.model.ScheduleWithCaptchaDTO;

import com.sena.crud_basic.service.SchedulesService;

@RestController
@RequestMapping("/api/v1/Schedules")
public class SchedulesController {


    @Autowired
    private SchedulesService SchedulesService;

    @PostMapping("/")
    public ResponseEntity<Object> registerSchedule(@RequestBody ScheduleWithCaptchaDTO scheduleWithCaptchaDTO) {
        responseDTO response = SchedulesService.save(scheduleWithCaptchaDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAllSchedule() {
        List<SchedulesDTO> listSchedule = SchedulesService.getAllSchedules();
        return new ResponseEntity<>(listSchedule, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdSchedule(@PathVariable int id) {
        SchedulesDTO schedule = SchedulesService.getScheduleById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @GetMapping("/search/{filter}")
    public ResponseEntity<Object> search(@PathVariable String filter) {
        List<SchedulesDTO> ListSchedule = SchedulesService.getFilterSchedule(filter);
        return new ResponseEntity<>(ListSchedule, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteSchedule(@RequestBody ScheduleWithCaptchaDTO scheduleWithCaptchaDTO) {
        responseDTO response = SchedulesService.delete(scheduleWithCaptchaDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
