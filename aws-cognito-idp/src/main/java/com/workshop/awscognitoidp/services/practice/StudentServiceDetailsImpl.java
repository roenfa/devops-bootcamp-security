package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.workshop.awscognitoidp.models.practice.StudentDetails;
import com.workshop.awscognitoidp.repositories.practice.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceDetailsImpl implements StudentServiceDetails {

  private final StudentRepository studentRepository;

  @Override
  public StudentDetails saveStudentDetails(StudentDetails student) {
    return studentRepository.save(student);
  }

  @Override
  public Optional<StudentDetails> getStudentDetailsById(Long studentId) {
    return studentRepository.findById(studentId);
  }

  @Override
  public boolean deleteStudentDetailsById(Long idStudent) {
    try {
      studentRepository.deleteById(idStudent);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
}
