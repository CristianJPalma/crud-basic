package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.InstructorsDTO;
import com.sena.crud_basic.repository.IinstructorsRepository;

@Service
public class InstructorsService {

    @Autowired
    private IinstructorsRepository iinstructorsRepository;

    public boolean save(InstructorsDTO instructor){
        iinstructorsRepository.save(instructor);
        return true;
    }
}
