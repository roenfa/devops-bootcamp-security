package com.workshop.awscognitoidp.controllers;

import com.workshop.awscognitoidp.models.Student;
import com.workshop.awscognitoidp.models.Subject;
import com.workshop.awscognitoidp.models.Trainer;
import com.workshop.awscognitoidp.repositories.StudentRepository;
import com.workshop.awscognitoidp.repositories.SubjectRepository;
import com.workshop.awscognitoidp.services.JWTDecoder;
import com.workshop.awscognitoidp.services.JwtValidator;
import com.workshop.awscognitoidp.services.StudentService;
import com.workshop.awscognitoidp.services.SubjectService;
import com.workshop.awscognitoidp.services.TrainerService;

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
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TrainerService trainerService;

    @GetMapping("/mysubjects")
    public Iterable<Subject> getSubjects(@RequestHeader("Authorization") String token) {
        if(jwtValidator.validateJwtToken(token.substring(7))){
            String email  = JWTDecoder.getEmail(token.substring(7));
            Student student = studentService.getStudentbyEmail(email);
            Iterable<Subject> subjects= subjectService.findAll();
            return subjects;
        }
        else {
            throw new AccessDeniedException("Invalid token");
        }
    }

    @GetMapping("/mydetails/{email}")
    public Object getMyDetails(@PathVariable(value = "email") String email ,@RequestHeader("Authorization") String token) {
      if(jwtValidator.validateJwtToken(token.substring(7))){ 
        Student Admin = studentService.getStudentbyEmail(email);
        Trainer Trainer = trainerService.getTrainerbyEmail(email);
        return (Admin.toString().length()>0) ? Admin : Trainer;
      }
      else {
          throw new AccessDeniedException("Invalid token");
      }
    }
}

