package com.workshop.awscognitoidp.controllers;

import com.workshop.awscognitoidp.models.Student;
import com.workshop.awscognitoidp.models.Subject;
import com.workshop.awscognitoidp.repositories.StudentRepository;
import com.workshop.awscognitoidp.repositories.SubjectRepository;
import com.workshop.awscognitoidp.services.JWTDecoder;
import com.workshop.awscognitoidp.services.JwtValidator;
import com.workshop.awscognitoidp.services.StudentService;
import com.workshop.awscognitoidp.services.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {
    @Autowired
    private JwtValidator jwtValidator;
    @Autowired
    private JWTDecoder JWTDecoder;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/mysubjects")
    public List<Subject> getSubjects(@RequestHeader("Authorization") String token) {
        if(jwtValidator.validateJwtToken(token.substring(7))){
            String email  = JWTDecoder.getEmail(token.substring(7));
            Student student = studentRepository.findByEmail(email);
            List<Subject> subjects= subjectRepository.findAll();
            return subjects;
        }
        else {
            throw new AccessDeniedException("Invalid token");
        }
    }

    @GetMapping("/mydetails")
    public Student getMyDetails(@RequestHeader("Authorization") String token) {
        if(jwtValidator.validateJwtToken(token.substring(7))){
            String email  = JWTDecoder.getEmail(token.substring(7));
            Student student = studentRepository.findByEmail(email);
            return student;
        }
        else {
            throw new AccessDeniedException("Invalid token");
        }
    }
}

