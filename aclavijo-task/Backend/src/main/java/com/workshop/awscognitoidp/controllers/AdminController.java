package com.workshop.awscognitoidp.controllers;

import com.workshop.awscognitoidp.models.Trainer;
import com.workshop.awscognitoidp.repositories.TrainerRepository;
import com.workshop.awscognitoidp.services.JWTDecoder;
import com.workshop.awscognitoidp.services.JwtValidator;
import com.workshop.awscognitoidp.services.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {
    @Autowired
    private JwtValidator jwtValidator;
    @Autowired
    private JWTDecoder JWTDecoder;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TrainerService trainerService;

    @GetMapping("/all")
    public List<Trainer> getSubjects(@RequestHeader("Authorization") String token) {
        if(jwtValidator.validateJwtToken(token.substring(7))){
            
            String role  = JWTDecoder.getRole(token.substring(7));
            if (role.equals("Admin")) {
                return trainerRepository.findAll();
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        }
        else {
            throw new AccessDeniedException("Invalid token");
        }
    }

    @PostMapping("/trainers")
    public void uploadStudent(@RequestHeader("Authorization") String token, @RequestBody Trainer trainer) {
        if (jwtValidator.validateJwtToken(token.substring(7))) {
            
            String role = JWTDecoder.getRole(token.substring(7));
            if (role.equals("Admin")) {
                trainerService.saveTrainer(trainer);
            } else {
                throw new AccessDeniedException("Insufficient permissions");
            }
        } else {
            throw new AccessDeniedException("Invalid token");
        }
    }
}

