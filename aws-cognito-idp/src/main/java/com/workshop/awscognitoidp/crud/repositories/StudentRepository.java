package com.workshop.awscognitoidp.crud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workshop.awscognitoidp.crud.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    
}
