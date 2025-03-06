package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.CoursesDTO;
import com.sena.crud_basic.service.CoursesService;

@RestController
public class CoursesController {

        @Autowired
    private CoursesService coursesService;
    @PostMapping("/courses")
    public String registerInstructor(@RequestBody CoursesDTO courses) {

        coursesService.save(courses);

        return "OK";
    }
}
