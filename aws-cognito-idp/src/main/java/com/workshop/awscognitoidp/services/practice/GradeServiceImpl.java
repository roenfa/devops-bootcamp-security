package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.workshop.awscognitoidp.models.practice.Grade;
import com.workshop.awscognitoidp.repositories.practice.GradeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService{

  private final GradeRepository gradeRepository;

  @Override
  public Optional<Grade> getGradeById(Long idGrade) {
    return gradeRepository.findById(idGrade);
  }
  
}
