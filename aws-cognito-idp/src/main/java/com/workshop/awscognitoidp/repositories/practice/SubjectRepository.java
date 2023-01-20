package com.workshop.awscognitoidp.repositories.practice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.awscognitoidp.models.practice.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
  
}
