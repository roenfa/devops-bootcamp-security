package com.workshop.awscognitoidp.controllers;

import com.workshop.awscognitoidp.models.Subject;
import com.workshop.awscognitoidp.repositories.SubjectRepository;
import com.workshop.awscognitoidp.services.JwtData;
import com.workshop.awscognitoidp.services.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/students")
public class ResultController {
    @Autowired
    private JwtValidator jwtValidator;
    @Autowired
    private JwtData jwtData;
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/results")
    public List<Subject> getResults(@RequestHeader("Authorization") String token) {
        if(!jwtValidator.validateJwtToken(token.substring(7))) {
            throw new AccessDeniedException("The Token is invalid");
        }

        System.out.println("The Token is valid");

        String role  = jwtData.getTokenRole(token.substring(7));

        if (!role.equals("trainer") && !role.equals("admin")) {
            throw new AccessDeniedException("Forbidden");
        }

        return subjectRepository.getAll();
    }

    @PostMapping("/results")
    public void uploadResults(@RequestHeader("Authorization") String token, @RequestBody Subject results) {
        if (!jwtValidator.validateJwtToken(token.substring(7))) {
            throw new AccessDeniedException("The Token is invalid");
        }
    
        String role = jwtData.getTokenRole(token.substring(7));
    
        if (!role.equals("trainer") && !role.equals("admin")) {
            throw new AccessDeniedException("Forbidden");
        }
    
        subjectRepository.addResult(results);
    }

    @GetMapping("/results/{id}")
    public List<Subject> getMyResults(@PathVariable(value = "id") String id, @RequestHeader("Authorization") String token) {
        if(!jwtValidator.validateJwtToken(token.substring(7))){
            throw new AccessDeniedException("The Token is invalid");
        }

        System.out.println("The Token is valid");
        
        String role  = jwtData.getTokenRole(token.substring(7));

        if (role.equals("student")) {
            return subjectRepository.getByStudentId(id);
        } else {
            throw new AccessDeniedException("Forbidden");
        }        
    }
}
