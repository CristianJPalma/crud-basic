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
import com.sena.crud_basic.model.CoursesDTO;
import com.sena.crud_basic.service.CoursesService;

@RestController
@RequestMapping("/api/v1/courses")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @PostMapping("/")
    public ResponseEntity<Object> registerCourse(
        @RequestBody CoursesDTO course) {
        responseDTO response = coursesService.save(course);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAllBook() {
        List<CoursesDTO> listCourse = coursesService.getAllCourses();
        return new ResponseEntity<>(listCourse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdBook(@PathVariable int id) {
        CoursesDTO course = coursesService.getCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/search/{filter}")
    public ResponseEntity<Object> search(@PathVariable String filter) {
        List<CoursesDTO> ListCourse = coursesService.getFilterCourse(filter);
        return new ResponseEntity<>(ListCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable int id) {
        responseDTO response = coursesService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
