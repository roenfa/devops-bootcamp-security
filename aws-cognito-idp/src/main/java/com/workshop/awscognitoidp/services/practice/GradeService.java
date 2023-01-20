package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import com.workshop.awscognitoidp.models.practice.Grade;

public interface GradeService {
  Optional<Grade> getGradeById(Long idGrade);
  
}
