package com.workshop.awscognitoidp.repositories.practice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.awscognitoidp.models.practice.SubjectStudent;
import com.workshop.awscognitoidp.models.practice.SubjectStudentId;

public interface SubjectStudentRepository extends JpaRepository <SubjectStudent, SubjectStudentId>{
  
}
