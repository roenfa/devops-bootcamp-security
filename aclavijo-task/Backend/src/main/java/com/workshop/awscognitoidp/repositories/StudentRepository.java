package com.workshop.awscognitoidp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.awscognitoidp.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
  Student findByEmail(String email);
  Student getById(Long id);
}