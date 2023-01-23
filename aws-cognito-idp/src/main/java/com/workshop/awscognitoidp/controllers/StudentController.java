package com.workshop.awscognitoidp.controllers;


import com.workshop.awscognitoidp.models.Student;
import com.workshop.awscognitoidp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PreAuthorize("hasAuthority('ROLE_TRAINER')")
    @GetMapping
    public ResponseEntity<List<Student>> retrieveAllStudents() {
        return ResponseEntity.ok(this.studentService.retrieveAllStudents());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Student> findUserByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(this.studentService.findUserByUsername(username));
    }

}
