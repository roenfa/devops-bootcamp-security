package com.workshop.awscognitoidp.controllers;

import com.workshop.awscognitoidp.models.Student;
import com.workshop.awscognitoidp.models.Subject;
import com.workshop.awscognitoidp.models.Trainer;
import com.workshop.awscognitoidp.services.JWTDecoder;
import com.workshop.awscognitoidp.services.JwtValidator;
import com.workshop.awscognitoidp.services.StudentService;
import com.workshop.awscognitoidp.services.SubjectService;
import com.workshop.awscognitoidp.services.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@RestController
@RequestMapping(path = "/api/trainers")
public class TrainerController {
    @Autowired
    private JwtValidator jwtValidator;
    @Autowired
    private JWTDecoder JWTDecoder;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TrainerService trainerService;

    
    @GetMapping("/students")
    public List<Student> getStudents(@RequestHeader("Authorization") String token) {
        if(jwtValidator.validateJwtToken(token.substring(7))){
            
            String role  = JWTDecoder.getRole(token.substring(7));
            if (role.equals("Trainer") || role.equals("Admin")) {
                return studentService.findAll();
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        }
        else {
            throw new AccessDeniedException("Invalid token");
        }
    }
    @GetMapping("/subjects")
    public Iterable<Subject> getSubjects(@RequestHeader("Authorization") String token) {
        if(jwtValidator.validateJwtToken(token.substring(7))){
            
            String role  = JWTDecoder.getRole(token.substring(7));
            if (role.equals("Trainer") || role.equals("Admin")) {
                return subjectService.findAll();
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        }
        else {
            throw new AccessDeniedException("Invalid token");
        }
    }
    @PostMapping("/student/{id1}/subject/{id2}/grades")
    public void uploadGrades(@PathVariable(value = "id1") Long id1,@PathVariable(value = "id2") Long id2,@RequestHeader("Authorization") String token, @RequestBody Subject subjects) {
        if (jwtValidator.validateJwtToken(token.substring(7))) {
            
            String role = JWTDecoder.getRole(token.substring(7));
            if (role.equals("Trainer") || role.equals("Admin")) {
                subjectService.saveSubject(subjects);
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        } else {
            throw new AccessDeniedException("Invalid token");
        }
    }
    @PostMapping("/subject")
    public void uploadSubject(@RequestHeader("Authorization") String token, @RequestBody Subject subjects) {
        if (jwtValidator.validateJwtToken(token.substring(7))) {
            
            String role = JWTDecoder.getRole(token.substring(7));
            if (role.equals("Trainer") || role.equals("Admin")) {
                subjectService.saveSubject(subjects);
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        } else {
            throw new AccessDeniedException("Invalid token");
        }
    }

    @PostMapping("/student")
    public void uploadStudent(@RequestHeader("Authorization") String token, @RequestBody Student student) {
        if (jwtValidator.validateJwtToken(token.substring(7))) {
            
            String role = JWTDecoder.getRole(token.substring(7));
            if (role.equals("Trainer") || role.equals("Admin")) {
                studentService.saveStudent(student);
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        } else {
            throw new AccessDeniedException("Invalid token");
        }
    }

    @PostMapping("/student/{id}")
    public void uploadStudent(@PathVariable(value = "id") Long id,@RequestHeader("Authorization") String token, @RequestBody Student student) throws Exception {
        if (jwtValidator.validateJwtToken(token.substring(7))) {
            String role = JWTDecoder.getRole(token.substring(7));
            if (role.equals("Trainer") || role.equals("Admin")) {
                if(studentService.getById(id).toString().length()>0){
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnitName");
                    EntityManager em = emf.createEntityManager();
                    em.getTransaction().begin();
                    org.hibernate.query.Query query = (org.hibernate.query.Query) em.createQuery("update Student s set s.firstName = :student.firstName,s.lastName = :student.lastName,s.email = :student.email,s.country = :student.country where s.id = :id");
                    query.setParameter("student",student);
                    query.setParameter("id",id);
                    int result = query.executeUpdate();
                    em.getTransaction().commit();
                    em.close();
                }
                else{
                    throw new Exception("Id not exists on DB");
                }
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        } else {
            throw new AccessDeniedException("Invalid token");
        }
    }


    @GetMapping("/student/{id}")
    public Student getMyScores(@PathVariable(value = "id") Long id, @RequestHeader("Authorization") String token) {
        if(jwtValidator.validateJwtToken(token.substring(7))){
            String role  = JWTDecoder.getRole(token.substring(7));
            if (role.equals("Trainer") || role.equals("Admin")) {
                return studentService.getById(id);
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        }
        else {
            throw new AccessDeniedException("Invalid token");
        }
    }
}

