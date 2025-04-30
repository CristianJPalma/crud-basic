package com.sena.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.LearnerWithCaptchaDTO;
import com.sena.crud_basic.model.LearnersDTO;


import com.sena.crud_basic.service.LearnersService;

@RestController
@RequestMapping("/api/v1/learners")
public class LearnersController {

 @Autowired
    private LearnersService learnersService;

    @PostMapping("/")
    public ResponseEntity<Object> registerLearner(@RequestBody LearnerWithCaptchaDTO learnerWithCaptchaDTO) {
        responseDTO response = learnersService.save(learnerWithCaptchaDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAllLearners() {
        List<LearnersDTO> listLearner = learnersService.getAllLearners();
        return new ResponseEntity<>(listLearner, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdLearner(@PathVariable int id) {
        LearnersDTO learner = learnersService.getLearnerById(id);
        return new ResponseEntity<>(learner, HttpStatus.OK);
    }

    @GetMapping("/search/{filter}")
    public ResponseEntity<Object> search(@PathVariable String filter) {
        List<LearnersDTO> ListLearner = learnersService.getFilterLearner(filter);
        return new ResponseEntity<>(ListLearner, HttpStatus.OK);
    }

    
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateLearner(@PathVariable int id, @RequestBody LearnersDTO learner) {
        responseDTO response = learnersService.update(id, learner);
        
        if("OK".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/")
    public ResponseEntity<Object> deleteLearner(@RequestBody LearnerWithCaptchaDTO learnerWithCaptchaDTO) {
        responseDTO response = learnersService.delete(learnerWithCaptchaDTO);
        
        if("OK".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
}
