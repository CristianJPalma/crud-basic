package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.EnrollmentsDTO;
import com.sena.crud_basic.service.EnrollmentsService;

@RestController
public class EnrollmentsController {

    @Autowired
    private EnrollmentsService enrollmentsService;
    @PostMapping("/enrollment")
    public String registerEnrollment(@RequestBody EnrollmentsDTO enrollment) {

        enrollmentsService.save(enrollment);

        return "OK";
    }
}
