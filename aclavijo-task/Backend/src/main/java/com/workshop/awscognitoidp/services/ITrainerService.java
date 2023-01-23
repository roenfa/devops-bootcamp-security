package com.workshop.awscognitoidp.services;

import java.util.List;

import com.workshop.awscognitoidp.models.Trainer;

public interface ITrainerService {
  Trainer saveTrainer(Trainer trainer);
  Trainer getTrainerbyEmail(String email);
  boolean deleteTrainerById(Long idTrainer);
  List<Trainer> findAll();
  Trainer getById(Long id);
}
