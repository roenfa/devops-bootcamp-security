package com.workshop.awscognitoidp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.workshop.awscognitoidp.models.Student;
import com.workshop.awscognitoidp.repositories.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {

  private final StudentRepository studentRepository;

 
  @Override
  public Student getById(Long id){
    return studentRepository.getById(id);
  }

  @Override
  public Student saveStudent(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public Student getStudentbyEmail(String email) {
    return studentRepository.findByEmail(email);
  }

  @Override
  public List<Student> findAll() {
    return studentRepository.findAll();
  }

  @Override
  public boolean deleteStudentById(Long idStudent) {
    try {
      studentRepository.deleteById(idStudent);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
}