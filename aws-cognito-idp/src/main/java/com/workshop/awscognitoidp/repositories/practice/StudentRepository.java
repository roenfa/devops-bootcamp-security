package com.workshop.awscognitoidp.repositories.practice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.awscognitoidp.models.practice.StudentDetails;

public interface StudentRepository extends JpaRepository<StudentDetails, Long>{
  
}