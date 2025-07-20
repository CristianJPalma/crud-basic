package com.sena.crud_basic.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.EnrollmentWithCaptchaDTO;
import com.sena.crud_basic.model.EnrollmentsDTO;
import com.sena.crud_basic.repository.IenrollmentsRepository;

@Service
public class EnrollmentsService {

    @Autowired
    private IenrollmentsRepository IenrollmentsRepository;
    
    @Autowired
    private CaptchaService captchaService;

    public List<EnrollmentsDTO> getAllEnrollments() {
        return IenrollmentsRepository.findAllEnrollmentsActive();
    }

    public List<EnrollmentsDTO> getFilterEnrollments(String filter) {
        return IenrollmentsRepository.search(filter);
    }

    public EnrollmentsDTO getEnrollmentById(int id) {
        return IenrollmentsRepository.findById(id).get();
    }

    public responseDTO save(EnrollmentsDTO enrollment) {
        if (enrollment.getId_learner()==null) {
            responseDTO response = new responseDTO(
                    "Error",
                    "El nombre debe tener algun valor");
            return response;
        }

        // añadir las n condiciones

        IenrollmentsRepository.save(enrollment);
        responseDTO response = new responseDTO(
                "OK",
                "Se registró correctamente");
        return response;
        // return true;
    }

    public responseDTO save(EnrollmentWithCaptchaDTO enrollmentWithCaptchaDTO) {
        boolean isCaptchaValid = captchaService.validateCaptcha(enrollmentWithCaptchaDTO.getRecaptchaToken());
        if (!isCaptchaValid) {
            return new responseDTO("Error", "Captcha inválido. La operación ha sido rechazada.");
        }
        EnrollmentsDTO enrollment = enrollmentWithCaptchaDTO.getEnrollment();
        if (enrollment.getId_learner() == null) {
            return new responseDTO("Error", "El aprendiz es obligatorio.");
        }
        if (enrollment.getId_course() == null) {
            return new responseDTO("Error", "El curso es obligatorio.");
        }
        if (enrollment.getEnrollment_date() == null) {
            return new responseDTO("Error", "La fecha de inscripción es obligatoria.");
        }
        IenrollmentsRepository.save(enrollment);
        return new responseDTO("OK", "Se registró correctamente");
    }


    public responseDTO delete(int id) {
        EnrollmentsDTO enrollment = getEnrollmentById(id);
        enrollment.setStatus(0);
        IenrollmentsRepository.save(enrollment);
        responseDTO response = new responseDTO(
                "OK",
                "Se eliminó correctamente");
        return response;
    }

    public responseDTO delete(EnrollmentWithCaptchaDTO enrollmentWithCaptchaDTO) {
        boolean isCaptchaValid = captchaService.validateCaptcha(enrollmentWithCaptchaDTO.getRecaptchaToken());
        if (!isCaptchaValid) {
            return new responseDTO("Error", "Captcha inválido. La operación ha sido rechazada.");
        }
        int id = enrollmentWithCaptchaDTO.getEnrollment().getId_enrollment();
        EnrollmentsDTO enrollment = getEnrollmentById(id);
        enrollment.setStatus(0);
        IenrollmentsRepository.save(enrollment);
        return new responseDTO("OK", "Se eliminó correctamente");
    }

    public responseDTO update(int id, EnrollmentsDTO updatedEnrollment) {
        EnrollmentsDTO existingEnrollment = getEnrollmentById(id);
        if (existingEnrollment == null) {
            return new responseDTO("Error", "Enrollment no encontrado");
        }
        // Actualiza solo los campos necesarios
        if (updatedEnrollment.getId_learner() != null) {
            existingEnrollment.setId_learner(updatedEnrollment.getId_learner());
        }
        if (updatedEnrollment.getId_course() != null) {
            existingEnrollment.setId_course(updatedEnrollment.getId_course());
        }
        if (updatedEnrollment.getEnrollment_date() != null) {
            existingEnrollment.setEnrollment_date(updatedEnrollment.getEnrollment_date());
        }
        // ...otros campos si es necesario...

        IenrollmentsRepository.save(existingEnrollment);
        return new responseDTO("OK", "Enrollment actualizado correctamente");
    }
}
