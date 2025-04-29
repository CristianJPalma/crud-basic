package com.sena.crud_basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.InstructorsDTO;
import com.sena.crud_basic.model.InstructorWithCaptchaDTO;
import com.sena.crud_basic.repository.IinstructorsRepository;

@Service
public class InstructorsService {

 @Autowired
    private IinstructorsRepository IinstructorsRepository;

    @Autowired
    private CaptchaService captchaService;

    public List<InstructorsDTO> getAllInstructors() {
        return IinstructorsRepository.findAllInstructorsActive();
    }

    public List<InstructorsDTO> getFilterInstructor(String filter) {
        return IinstructorsRepository.search(filter);
    }

    public InstructorsDTO getInstructorById(int id) {
        return IinstructorsRepository.findById(id).get();
    }

    public responseDTO save(InstructorWithCaptchaDTO instructorWithCaptchaDTO) {
        // Primero, validamos el captcha usando el token que viene en el DTO
        boolean isCaptchaValid = captchaService.validateCaptcha(instructorWithCaptchaDTO.getRecaptchaToken());

        if (!isCaptchaValid) {
            // Si el captcha no es válido, devolvemos un mensaje de error
            responseDTO response = new responseDTO(
                    "Error",
                    "Captcha inválido. La operación ha sido rechazada.");
            return response;
        }
        
        // Si el captcha es válido, procedemos con la lógica original de guardar el instructor
        if(instructorWithCaptchaDTO.getInstructor().getFirst_name().length() < 1 || instructorWithCaptchaDTO.getInstructor().getFirst_name().length() > 20) {
            responseDTO response = new responseDTO(
                    "Error",
                    "El nombre debe tener una longitud entre 1 y 20 caracteres");
            return response;
        }

        if(instructorWithCaptchaDTO.getInstructor().getLast_name().length() < 1 || instructorWithCaptchaDTO.getInstructor().getLast_name().length() > 20) {
            responseDTO response = new responseDTO(
                    "Error",
                    "El apellido debe tener una longitud entre 1 y 20 caracteres");
            return response;
        }
        
        IinstructorsRepository.save(instructorWithCaptchaDTO.getInstructor());
        responseDTO response = new responseDTO(
            "OK",
            "Se registró correctamente");
        return response;
    }


    public responseDTO delete(int id) {
        InstructorsDTO instructor = getInstructorById(id);
        instructor.setStatus(0);
        IinstructorsRepository.save(instructor);
        responseDTO response = new responseDTO(
                "OK",
                "Se eliminó correctamente");
        return response;
    }
}
