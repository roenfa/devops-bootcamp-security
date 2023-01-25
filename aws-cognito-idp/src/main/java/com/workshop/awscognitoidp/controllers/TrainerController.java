package com.workshop.awscognitoidp.controllers;


import com.workshop.awscognitoidp.models.Trainer;
import com.workshop.awscognitoidp.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin( origins = "*")
@RequestMapping("/api/trainers")
public class TrainerController {


    @Autowired
    private TrainerService trainerService;


    @PreAuthorize("hasAuthority('ROLE_TRAINER')")
    @GetMapping
    public ResponseEntity<List<Trainer>> retrieveAllTrainers() {
        return ResponseEntity.ok(this.trainerService.retrieveAllTrainers());
    }

}
