package com.sena.crud_basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.CoursesDTO;
import com.sena.crud_basic.repository.IcoursesRepository;

@Service
public class CoursesService {

    @Autowired
    private IcoursesRepository IcoursesRepository;

    public List<CoursesDTO> getAllCourses() {
        return IcoursesRepository.findAllCoursesActive();
    }

    public List<CoursesDTO> getFilterCourse(String filter) {
        return IcoursesRepository.search(filter);
    }

    public CoursesDTO getCourseById(int id) {
        return IcoursesRepository.findById(id).get();
    }

    public responseDTO save(CoursesDTO course) {
        if (course.getCourse_name().length() < 1 || course.getCourse_name().length() > 20) {
            responseDTO response = new responseDTO(
                    "Error",
                    "El nombre debe tener una longitud entre 1 y 255 caracteres");
            return response;
        }

        IcoursesRepository.save(course);
        responseDTO response = new responseDTO(
                "OK",
                "Se registró correctamente");
        return response;
        // return true;
    }
    public responseDTO update(int id, CoursesDTO updatedCourse) {
        // Buscar el curso por su ID
        CoursesDTO existingCourse = getCourseById(id);
    
        // Validar si el curso existe
        if (existingCourse == null) {
            responseDTO response = new responseDTO(
                    "Error",
                    "Curso no encontrado");
            return response;
        }
    
        // Solo actualizamos los campos que no sean nulos en el objeto actualizado
        if (updatedCourse.getCourse_name() != null && !updatedCourse.getCourse_name().isEmpty()) {
            existingCourse.setCourse_name(updatedCourse.getCourse_name());
        }

    
        // Realizar la actualización en la base de datos
        IcoursesRepository.save(existingCourse);
        
        responseDTO response = new responseDTO(
                "OK",
                "Curso actualizado correctamente");
        return response;
    }
    

    public responseDTO delete(int id) {
        CoursesDTO course = getCourseById(id);
        course.setStatus(0);
        IcoursesRepository.save(course);
        responseDTO response = new responseDTO(
                "OK",
                "Se eliminó correctamente");
        return response;
    }
}
