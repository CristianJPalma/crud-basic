package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.CoursesDTO;
import com.sena.crud_basic.repository.IcoursesRepository;

@Service
public class CoursesService {

    @Autowired
    private IcoursesRepository icoursesRepository;

    public boolean save(CoursesDTO courses){
        icoursesRepository.save(courses);
        return true;
    }
}
