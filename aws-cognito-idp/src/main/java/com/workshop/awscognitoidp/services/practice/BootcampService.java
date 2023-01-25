package com.workshop.awscognitoidp.services.practice;

import com.workshop.awscognitoidp.models.practice.Bootcamp;
import com.workshop.awscognitoidp.repositories.BootcampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BootcampService {

    @Autowired
    private BootcampRepository bootcampRepository;

    public Bootcamp saveBootcamp(Bootcamp bootcamp) {
        return bootcampRepository.save(bootcamp);
    }

    public List<Bootcamp> getAllBootcamps() {
        return bootcampRepository.findAll();
    }

}
