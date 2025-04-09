package com.sena.crud_basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.InstructorsDTO;
import com.sena.crud_basic.repository.IinstructorsRepository;

@Service
public class InstructorsService {

 @Autowired
    private IinstructorsRepository IinstructorsRepository;

    public List<InstructorsDTO> getAllInstructors() {
        return IinstructorsRepository.findAllInstructorsActive();
    }

    public List<InstructorsDTO> getFilterInstructor(String filter) {
        return IinstructorsRepository.search(filter);
    }

    public InstructorsDTO getInstructorById(int id) {
        return IinstructorsRepository.findById(id).get();
    }

    public responseDTO save(InstructorsDTO instructor) {
        if (instructor.getFirst_name().length() < 1 || instructor.getFirst_name().length() > 20) {
            responseDTO response = new responseDTO(
                    "Error",
                    "El nombre debe tener una longitud entre 1 y 20 caracteres");
            return response;
        }

        // añadir las n condiciones

        IinstructorsRepository.save(instructor);
        responseDTO response = new responseDTO(
                "OK",
                "Se registró correctamente");
        return response;
        // return true;
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
