package com.sena.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.InstructorWithCaptchaDTO;
import com.sena.crud_basic.model.InstructorsDTO;
import com.sena.crud_basic.service.InstructorsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorsController {

@Autowired
    private InstructorsService InstructorsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerInstructor(@RequestBody InstructorWithCaptchaDTO instructorWithCaptchaDTO) {
        responseDTO response = InstructorsService.save(instructorWithCaptchaDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAllInstuctors() {
        List<InstructorsDTO> listInstructor = InstructorsService.getAllInstructors();
        return new ResponseEntity<>(listInstructor, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdInstructor(@PathVariable int id) {
        InstructorsDTO instructor = InstructorsService.getInstructorById(id);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @GetMapping("/search/{filter}")
    public ResponseEntity<Object> search(@PathVariable String filter) {
        List<InstructorsDTO> listInstructor = InstructorsService.getFilterInstructor(filter);
        return new ResponseEntity<>(listInstructor, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateInstructor(@PathVariable int id, @RequestBody InstructorsDTO updatedInstructor) {
        responseDTO response = InstructorsService.update(id, updatedInstructor);
        
        if("OK".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/")
    public ResponseEntity<Object> deleteInstructor(@RequestBody InstructorWithCaptchaDTO instructorWithCaptchaDTO) {
        responseDTO response = InstructorsService.delete(instructorWithCaptchaDTO);
        
        if("OK".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
}
