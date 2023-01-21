package com.workshop.awscognitoidp.controllers;

import com.workshop.awscognitoidp.filters.JwtRequestFilter;
import com.workshop.awscognitoidp.models.Score;
import com.workshop.awscognitoidp.models.Subject;
import com.workshop.awscognitoidp.repositories.ISubjects;
import com.workshop.awscognitoidp.repositories.SubjectRepository;
import com.workshop.awscognitoidp.services.JwtDataRetriever;
import com.workshop.awscognitoidp.services.JwtValidator;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/students")
public class ScoreController {
    @Autowired
    private JwtValidator jwtValidator;
    @Autowired
    private JwtDataRetriever jwtDataRetriever;
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/scores")
    public List<Subject> getScores(@RequestHeader("Authorization") String token) {
        if(jwtValidator.validateJwtToken(token.substring(7))){
            System.out.println("TOKEN VALID");
            String role  = jwtDataRetriever.getTokenRole(token.substring(7));
            if (role.equals("trainer")) {
                return subjectRepository.getAll();
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        }
        else {
            throw new AccessDeniedException("Invalid token");
        }
    }

    @PostMapping("/scores")
    public void uploadScores(@RequestHeader("Authorization") String token, @RequestBody Subject scores) {
        if (jwtValidator.validateJwtToken(token.substring(7))) {
            System.out.println("TOKEN VALID");
            String role = jwtDataRetriever.getTokenRole(token.substring(7));
            System.out.println("role in scores: " + role);
            if (role.equals("trainer")) {
                subjectRepository.addScore(scores);
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        } else {
            throw new AccessDeniedException("Invalid token");
        }
    }

    @GetMapping("/scores/{id}")
    public List<Subject> getMyScores(@PathVariable(value = "id") String id, @RequestHeader("Authorization") String token) {
        if(jwtValidator.validateJwtToken(token.substring(7))){
            System.out.println("TOKEN VALID");
            String role  = jwtDataRetriever.getTokenRole(token.substring(7));
            if (role.equals("student")) {
                return subjectRepository.getByStudentId(id);
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        }
        else {
            throw new AccessDeniedException("Invalid token");
        }
    }
}

