package com.workshop.awscognitoidp.crud.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import com.workshop.awscognitoidp.crud.models.Student;
import com.workshop.awscognitoidp.crud.models.StudentGrade;
import com.workshop.awscognitoidp.crud.models.Subject;
import com.workshop.awscognitoidp.crud.repositories.StudentRepository;
import com.workshop.awscognitoidp.crud.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class StudentServiceImp implements Service<Student>{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Student> getAll() {
        var it = studentRepository.findAll();
        var orders = new ArrayList<Student>();
        it.forEach(e -> {
            Hibernate.initialize(e.getStudentGrades());
            orders.add(e);
        });

        return orders;
    }

    @Override
    public Student getById(Long id) {
        Student o = studentRepository.findById(id).get();
        return o;
    }

    @Override
    public Student insert(Student o) {
        o.getStudentGrades()
            .stream()
            .map(p -> {
                Subject subject = subjectRepository.findById(p.getSubject().getId()).get();
                StudentGrade studentGrade = new StudentGrade(p.getGrade(), subject);
                return studentGrade;
            });

        return studentRepository.save(o);
    }

    @Override
    public Student update(Long id, Student o) {
        Student orderToUpdate = studentRepository.findById(id).get();
        Student orderUpdated = null;
        if (orderToUpdate != null) {
            orderUpdated = studentRepository.save(o);
         }

        return orderUpdated;
    }

    @Override
    public Student delete(Long id) {
        Student order = studentRepository.findById(id).get();
        
        if (order != null) {
           studentRepository.delete(order);
        }

        return order;
    }
}
