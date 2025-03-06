package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.EnrollmentsDTO;
import com.sena.crud_basic.repository.IenrollmentsRepository;

@Service
public class EnrollmentsService {

    @Autowired
    private IenrollmentsRepository ienrollmentsRepository;

    public boolean save(EnrollmentsDTO enrollment){
        ienrollmentsRepository.save(enrollment);
        return true;
    }
}
