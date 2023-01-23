package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import com.workshop.awscognitoidp.models.practice.StudentDetails;

public interface StudentServiceDetails {
  StudentDetails saveStudentDetails(StudentDetails student);
  Optional<StudentDetails> getStudentDetailsById(Long idStudent);
  boolean deleteStudentDetailsById(Long idStudent);
}
