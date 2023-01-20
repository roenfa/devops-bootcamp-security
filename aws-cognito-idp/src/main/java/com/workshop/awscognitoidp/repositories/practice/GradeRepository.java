package com.workshop.awscognitoidp.repositories.practice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.awscognitoidp.models.practice.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
  
}
