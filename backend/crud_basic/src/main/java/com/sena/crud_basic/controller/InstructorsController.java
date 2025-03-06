package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.InstructorsDTO;
import com.sena.crud_basic.service.InstructorsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class InstructorsController {

    @Autowired
    private InstructorsService instructorsService;
    @PostMapping("/instructor")
    public String registerInstructor(@RequestBody InstructorsDTO instructor) {

        instructorsService.save(instructor);

        return "OK";
    }
    
}
