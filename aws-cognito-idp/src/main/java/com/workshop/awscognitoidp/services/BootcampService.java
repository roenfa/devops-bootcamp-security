package com.workshop.awscognitoidp.services;

import com.workshop.awscognitoidp.models.Bootcamp;
import org.springframework.stereotype.Service;

@Service
public class BootcampService {

    public Bootcamp[] retrieveAllBootcamps() {
        return Bootcamp.values();
    }

}
