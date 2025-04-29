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
import com.sena.crud_basic.model.CoursesDTO;
import com.sena.crud_basic.model.CourseWithCaptchaDTO;
import com.sena.crud_basic.service.CoursesService;

@RestController
@RequestMapping("/api/v1/courses")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    // Cambiar el tipo de entrada de CoursesDTO a CourseWithCaptchaDTO
    @PostMapping("/")
    public ResponseEntity<Object> registerCourse(@RequestBody CourseWithCaptchaDTO courseWithCaptchaDTO) {
        // Pasar el DTO con el captcha al servicio
        responseDTO response = coursesService.save(courseWithCaptchaDTO);
        
        // Devolver la respuesta correspondiente, que puede ser un error o éxito
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAllCourses() {
        List<CoursesDTO> listCourse = coursesService.getAllCourses();
        return new ResponseEntity<>(listCourse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdCourse(@PathVariable int id) {
        CoursesDTO course = coursesService.getCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/search/{filter}")
    public ResponseEntity<Object> search(@PathVariable String filter) {
        List<CoursesDTO> ListCourse = coursesService.getFilterCourse(filter);
        return new ResponseEntity<>(ListCourse, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable int id, @RequestBody CoursesDTO updatedCourse) {
        responseDTO response = coursesService.update(id, updatedCourse);
        
        // Retornar un código de estado adecuado dependiendo de si fue exitoso o no
        if ("OK".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/")
    public ResponseEntity<Object> deleteCourse(@RequestBody CourseWithCaptchaDTO courseWithCaptchaDTO) {
        responseDTO response = coursesService.delete(courseWithCaptchaDTO);

        if ("OK".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
