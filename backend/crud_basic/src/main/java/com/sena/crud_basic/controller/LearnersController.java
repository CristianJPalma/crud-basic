package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.LearnersDTO;
import com.sena.crud_basic.service.LearnersService;

@RestController
public class LearnersController {

    @Autowired
    private LearnersService learnersService;
    @PostMapping("/learner")
    public String registerLearner(@RequestBody LearnersDTO learner) {
        
        learnersService.save(learner);
        return "OK";
    }
}
