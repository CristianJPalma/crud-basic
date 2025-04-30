package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.LearnerWithCaptchaDTO;
import com.sena.crud_basic.model.LearnersDTO;
import com.sena.crud_basic.repository.IlearnersRepository;

@Service
public class LearnersService {

    @Autowired
    private IlearnersRepository IlearnersRepository;

    
    @Autowired
    private CaptchaService captchaService;

    public List<LearnersDTO> getAllLearners() {
        return IlearnersRepository.findAllLearnersActive();
    }

    public List<LearnersDTO> getFilterLearner(String filter) {
        return IlearnersRepository.search(filter);
    }

    public LearnersDTO getLearnerById(int id) {
        return IlearnersRepository.findById(id).get();
    }

    public responseDTO save(LearnerWithCaptchaDTO learnerWithCaptchaDTO) {
        // Primero, validamos el captcha usando el token que viene en el DTO
        boolean isCaptchaValid = captchaService.validateCaptcha(learnerWithCaptchaDTO.getRecaptchaToken());

        if (!isCaptchaValid) {
            // Si el captcha no es válido, devolvemos un mensaje de error
            responseDTO response = new responseDTO(
                    "Error",
                    "Captcha inválido. La operación ha sido rechazada.");
            return response;
        }

        // Si el captcha es válido, procedemos con la lógica original de guardar el learner
        if (learnerWithCaptchaDTO.getLearner().getFirst_name().length() < 1 || learnerWithCaptchaDTO.getLearner().getFirst_name().length() > 20) {
            responseDTO response = new responseDTO(
                    "Error",
                    "El nombre debe tener una longitud entre 1 y 20 caracteres");
            return response;
        }
                
        if (learnerWithCaptchaDTO.getLearner().getLast_name().length() < 1 || learnerWithCaptchaDTO.getLearner().getLast_name().length() > 20) {
            responseDTO response = new responseDTO(
                    "Error",
                    "El nombre debe tener una longitud entre 1 y 20 caracteres");
            return response;
        }

        IlearnersRepository.save(learnerWithCaptchaDTO.getLearner());
        responseDTO response = new responseDTO(
                "OK",
                "Se registró correctamente");
        return response;
    }


    public responseDTO update(int id, LearnersDTO updatedLearner) {
        // Buscar el learner por su ID
        LearnersDTO existingLearner = getLearnerById(id);

        // Validar si el learner existe
        if (existingLearner == null) {
            responseDTO response = new responseDTO(
                    "Error",
                    "Learner no encontrado");
            return response;
        }

        // Solo actualizamos los campos que no sean nulos en el objeto actualizado
        if (updatedLearner.getFirst_name() != null && !updatedLearner.getFirst_name().isEmpty()) {
            existingLearner.setFirst_name(updatedLearner.getFirst_name());
        }
        if (updatedLearner.getLast_name() != null && !updatedLearner.getLast_name().isEmpty()) {
            existingLearner.setLast_name(updatedLearner.getLast_name());
        }

        IlearnersRepository.save(existingLearner);
        responseDTO response = new responseDTO(
                "OK",
                "Learner actualizado correctamente");
        return response;
    }


    
    public responseDTO delete(LearnerWithCaptchaDTO learnerWithCaptchaDTO) {
        // Validar el captcha usando el token que viene en el DTO
        boolean isCaptchaValid = captchaService.validateCaptcha(learnerWithCaptchaDTO.getRecaptchaToken());

        if (!isCaptchaValid) {
            return new responseDTO(
                    "Error",
                    "Captcha inválido. La operación ha sido rechazada.");
        }

        // Obtener el ID del learner desde el DTO
        int id = learnerWithCaptchaDTO.getLearner().getId_learner();

        // Buscar el learner por su ID
        Optional<LearnersDTO> optionalLearner = IlearnersRepository.findById(id);
        if (optionalLearner.isEmpty()) {
            return new responseDTO(
                    "Error",
                    "El learner que intentas eliminar no existe.");
            
        }
        // Cambiar el estado del learner a "eliminado" (status = 0)
        LearnersDTO learner = optionalLearner.get();
        learner.setStatus(0);
        IlearnersRepository.save(learner);
        return new responseDTO(
                "OK",
                "Learner eliminado correctamente.");
    }
            
        
    
}
