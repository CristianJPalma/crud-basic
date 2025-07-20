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
import org.springframework.web.bind.annotation.PatchMapping;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.EnrollmentWithCaptchaDTO;
import com.sena.crud_basic.model.EnrollmentsDTO;
import com.sena.crud_basic.service.CoursesService;
import com.sena.crud_basic.service.EnrollmentsService;
import com.sena.crud_basic.service.LearnersService;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentsController {

    @Autowired
    private EnrollmentsService EnrollmentsService;

    @Autowired
    private LearnersService learnersService;

    @Autowired
    private CoursesService coursesService;

    @GetMapping("/learners")
    public ResponseEntity<Object> getAllLearnersForEnrollments() {
        return new ResponseEntity<>(learnersService.getAllLearnersSimple(), HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<Object> getAllCoursesForEnrollments() {
        return new ResponseEntity<>(coursesService.getAllCoursesSimple(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> registerEnrollment(@RequestBody EnrollmentWithCaptchaDTO enrollmentWithCaptchaDTO) {
        responseDTO response = EnrollmentsService.save(enrollmentWithCaptchaDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAllEnrollment() {
        List<EnrollmentsDTO> listEnrollment = EnrollmentsService.getAllEnrollments();
        return new ResponseEntity<>(listEnrollment, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdEnrollment(@PathVariable int id) {
        EnrollmentsDTO enrollment = EnrollmentsService.getEnrollmentById(id);
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

    @GetMapping("/search/{filter}")
    public ResponseEntity<Object> search(@PathVariable String filter) {
        List<EnrollmentsDTO> listEnrollment = EnrollmentsService.getFilterEnrollments(filter);
        return new ResponseEntity<>(listEnrollment, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteEnrollment(@RequestBody EnrollmentWithCaptchaDTO enrollmentWithCaptchaDTO) {
        responseDTO response = EnrollmentsService.delete(enrollmentWithCaptchaDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateEnrollment(@PathVariable int id, @RequestBody EnrollmentsDTO updatedEnrollment) {
        responseDTO response = EnrollmentsService.update(id, updatedEnrollment);
        if ("OK".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
