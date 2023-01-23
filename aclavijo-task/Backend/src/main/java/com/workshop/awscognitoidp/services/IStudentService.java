package com.workshop.awscognitoidp.services;

import java.util.List;

import com.workshop.awscognitoidp.models.Student;

public interface IStudentService {
  Student saveStudent(Student student);
  Student getStudentbyEmail(String email);
  boolean deleteStudentById(Long idStudent);
  List<Student> findAll();
  Student getById(Long id);
}