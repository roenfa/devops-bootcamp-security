package com.workshop.awscognitoidp.crud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workshop.awscognitoidp.crud.models.Trainer;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Long> {
    
}
