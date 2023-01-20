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
  public StudentDetails saveServiceDetails(StudentDetails student) {
    return studentRepository.save(student);
  }

  @Override
  public Optional<StudentDetails> getServiceDetailsById(Long idStudent) {
    return studentRepository.findById(idStudent);
  }

  @Override
  public boolean deleteServiceDetailsById(Long idStudent) {
    try {
      studentRepository.deleteById(idStudent);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
}
