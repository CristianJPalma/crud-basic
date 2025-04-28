package com.sena.crud_basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.CoursesDTO;
import com.sena.crud_basic.model.CourseWithCaptchaDTO;
import com.sena.crud_basic.repository.IcoursesRepository;

@Service
public class CoursesService {

    @Autowired
    private IcoursesRepository IcoursesRepository;

    @Autowired
    private CaptchaService captchaService;

    public List<CoursesDTO> getAllCourses() {
        return IcoursesRepository.findAllCoursesActive();
    }

    public List<CoursesDTO> getFilterCourse(String filter) {
        return IcoursesRepository.search(filter);
    }

    public CoursesDTO getCourseById(int id) {
        return IcoursesRepository.findById(id).get();
    }

    public responseDTO save(CourseWithCaptchaDTO courseWithCaptchaDTO) {
        // Primero, validamos el captcha usando el token que viene en el DTO
        boolean isCaptchaValid = captchaService.validateCaptcha(courseWithCaptchaDTO.getRecaptchaToken());

        if (!isCaptchaValid) {
            // Si el captcha no es válido, devolvemos un mensaje de error
            responseDTO response = new responseDTO(
                    "Error",
                    "Captcha inválido. La operación ha sido rechazada.");
            return response;
        }

        // Si el captcha es válido, procedemos con la lógica original de guardar el curso
        if (courseWithCaptchaDTO.getCourse().getCourse_name().length() < 1 || courseWithCaptchaDTO.getCourse().getCourse_name().length() > 20) {
            responseDTO response = new responseDTO(
                    "Error",
                    "El nombre debe tener una longitud entre 1 y 255 caracteres");
            return response;
        }

        IcoursesRepository.save(courseWithCaptchaDTO.getCourse());
        responseDTO response = new responseDTO(
                "OK",
                "Se registró correctamente");
        return response;
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
