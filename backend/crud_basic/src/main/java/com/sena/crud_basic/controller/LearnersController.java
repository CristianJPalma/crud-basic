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

import com.sena.crud_basic.model.LearnersDTO;

import com.sena.crud_basic.service.LearnersService;

@RestController
@RequestMapping("/api/v1/learners")
public class LearnersController {

 @Autowired
    private LearnersService learnersService;

    @PostMapping("/")
    public ResponseEntity<Object> registerLearner(
        @RequestBody LearnersDTO learner) {
        responseDTO response = learnersService.save(learner);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLearner(@PathVariable int id) {
        responseDTO response = learnersService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
