package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.instructoresDTO;
import com.sena.crud_basic.service.InstructoreService;


@RestController
public class InstructoresController {

    @Autowired
    private InstructoreService instructoreService;

    @PostMapping("/")
    public String registerInstructor(@RequestBody instructoresDTO instructor) {
        
        instructoreService.save(instructor);
        return "OK";
    }
    
}
