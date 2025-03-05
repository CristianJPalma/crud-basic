package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.LearnersDTO;
import com.sena.crud_basic.repository.IlearnersRepository;

@Service
public class LearnersService {

    @Autowired
    private IlearnersRepository ilearnersRepository;

    public boolean save(LearnersDTO learner){
        ilearnersRepository.save(learner);
        return true;
    }
}
