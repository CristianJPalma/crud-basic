package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

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

    public responseDTO update(int id, InstructorsDTO updatedInstructor) {
        // Buscar el instructor por su ID
        InstructorsDTO existingInstructor = getInstructorById(id);

        // Validar si el instructor existe
        if (existingInstructor == null) {
            responseDTO response = new responseDTO(
                    "Error",
                    "Instructor no encontrado");
            return response;
        }

        // Solo actualizamos los campos que no sean nulos en el objeto actualizado
        if (updatedInstructor.getFirst_name() != null && !updatedInstructor.getFirst_name().isEmpty()) {
            existingInstructor.setFirst_name(updatedInstructor.getFirst_name());
        }

        if (updatedInstructor.getLast_name() != null && !updatedInstructor.getLast_name().isEmpty()) {
            existingInstructor.setLast_name(updatedInstructor.getLast_name());
        }

        // Realizar la actualización en la base de datos
        IinstructorsRepository.save(existingInstructor);

        responseDTO response = new responseDTO(
                "OK",
                "Instructor actualizado correctamente");
        return response;
    }

    public responseDTO delete(InstructorWithCaptchaDTO instructorWithCaptchaDTO) {
        // Validar el captcha usando el token que viene en el DTO
        boolean isCaptchaValid = captchaService.validateCaptcha(instructorWithCaptchaDTO.getRecaptchaToken());

        if (!isCaptchaValid) {
            return new responseDTO(
                    "Error",
                    "Captcha inválido. La operación ha sido rechazada.");
        }

        // Obtener el ID del instructor desde el DTO
        int id = instructorWithCaptchaDTO.getInstructor().getId_instructor();

        // Buscar el instructor por su ID
        Optional<InstructorsDTO> optionalInstructor = IinstructorsRepository.findById(id);
        if (optionalInstructor.isEmpty()) {
            return new responseDTO(
                    "Error",
                    "El instructor que intentas eliminar no existe.");
            
        }
        // Cambiar el estado del instructor a "eliminado" (status = 0)
        InstructorsDTO instructor = optionalInstructor.get();
        instructor.setStatus(0);
        IinstructorsRepository.save(instructor);
        return new responseDTO(
                "OK",
                "Instructor eliminado correctamente.");
    }
}
