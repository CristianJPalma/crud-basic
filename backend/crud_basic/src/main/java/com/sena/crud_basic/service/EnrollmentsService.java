package com.sena.crud_basic.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.EnrollmentsDTO;
import com.sena.crud_basic.repository.IenrollmentsRepository;

@Service
public class EnrollmentsService {

    @Autowired
    private IenrollmentsRepository IenrollmentsRepository;

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


    public responseDTO delete(int id) {
        EnrollmentsDTO enrollment = getEnrollmentById(id);
        enrollment.setStatus(0);
        IenrollmentsRepository.save(enrollment);
        responseDTO response = new responseDTO(
                "OK",
                "Se eliminó correctamente");
        return response;
    }
}
