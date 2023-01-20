package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import com.workshop.awscognitoidp.models.practice.StudentDetails;

public interface StudentServiceDetails {
  StudentDetails saveServiceDetails(StudentDetails student);
  Optional<StudentDetails> getServiceDetailsById(Long idStudent);
  boolean deleteServiceDetailsById(Long idStudent);
}
