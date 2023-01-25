package com.workshop.awscognitoidp.controllers;

import com.workshop.awscognitoidp.models.Bootcamp;
import com.workshop.awscognitoidp.services.BootcampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( origins = "*")
@RequestMapping("/api/bootcamps")
public class BootcampController {

    @Autowired
    public BootcampService bootcampService;


    @PreAuthorize("hasAuthority('ROLE_TRAINER')")
    @GetMapping
    public ResponseEntity<Bootcamp[]> retrieveAllBootcamps() {
        return ResponseEntity.ok(this.bootcampService.retrieveAllBootcamps());
    }

}
