package com.workshop.awscognitoidp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.awscognitoidp.models.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
  Trainer findByEmail(String email);
  Trainer getById(Long id);
}
