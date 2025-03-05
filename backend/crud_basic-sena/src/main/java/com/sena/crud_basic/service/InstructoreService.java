package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.instructoresDTO;
import com.sena.crud_basic.repository.IinstructoresRepository;

@Service
public class InstructoreService {

    @Autowired
    private  IinstructoresRepository iinstructoresRepository;

    public boolean save(instructoresDTO instructor){
        iinstructoresRepository.save(instructor);
        return true;
    }
}
