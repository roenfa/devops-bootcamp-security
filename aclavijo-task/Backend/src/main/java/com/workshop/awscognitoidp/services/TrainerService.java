package com.workshop.awscognitoidp.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.workshop.awscognitoidp.models.Trainer;
import com.workshop.awscognitoidp.repositories.TrainerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrainerService implements ITrainerService {
  private final TrainerRepository trainerRepository;

 
  @Override
  public Trainer getById(Long id){
    return trainerRepository.getById(id);
  }

  @Override
  public Trainer saveTrainer(Trainer trainer) {
    return trainerRepository.save(trainer);
  }

  @Override
  public Trainer getTrainerbyEmail(String email) {
    return trainerRepository.findByEmail(email);
  }

  @Override
  public List<Trainer> findAll() {
    return trainerRepository.findAll();
  }

  @Override
  public boolean deleteTrainerById(Long idTrainer) {
    try {
      trainerRepository.deleteById(idTrainer);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
