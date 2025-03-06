package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.SchedulesDTO;
import com.sena.crud_basic.service.SchedulesService;

@RestController
public class SchedulesController {

    @Autowired
    private SchedulesService schedulesService;
    @PostMapping("/schedules")
    public String registerSchedule(@RequestBody SchedulesDTO schedule) {

        schedulesService.save(schedule);

        return "OK";
    }


}
