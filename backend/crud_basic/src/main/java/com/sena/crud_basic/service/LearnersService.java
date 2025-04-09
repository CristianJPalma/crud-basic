package com.sena.crud_basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.LearnersDTO;
import com.sena.crud_basic.repository.IlearnersRepository;

@Service
public class LearnersService {

@Autowired
    private IlearnersRepository IlearnersRepository;

    public List<LearnersDTO> getAllLearners() {
        return IlearnersRepository.findAllLearnersActive();
    }

    public List<LearnersDTO> getFilterLearner(String filter) {
        return IlearnersRepository.search(filter);
    }

    public LearnersDTO getLearnerById(int id) {
        return IlearnersRepository.findById(id).get();
    }

    public responseDTO save(LearnersDTO learner) {
        if (learner.getFirst_name().length() < 1 || learner.getFirst_name().length() > 20) {
            responseDTO response = new responseDTO(
                    "Error",
                    "El nombre debe tener una longitud entre 1 y 20 caracteres");
            return response;
        }

        // añadir las n condiciones

        IlearnersRepository.save(learner);
        responseDTO response = new responseDTO(
                "OK",
                "Se registró correctamente");
        return response;
        // return true;
    }
    public responseDTO delete(int id) {
        LearnersDTO learner = getLearnerById(id);
        learner.setStatus(0);
        IlearnersRepository.save(learner);
        responseDTO response = new responseDTO(
                "OK",
                "Se eliminó correctamente");
        return response;
    }
}
