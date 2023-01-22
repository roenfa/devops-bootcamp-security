package com.workshop.awscognitoidp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import com.workshop.awscognitoidp.crud.models.Bootcamp;
import com.workshop.awscognitoidp.crud.models.Trainer;
import com.workshop.awscognitoidp.crud.services.TrainerServiceImp;

@RestController
@RequestMapping(path = "/api/trainers")
public class TrainersController {
    @Autowired
    private TrainerServiceImp trainerService;

    @GetMapping
    public List<Trainer> getAll() {
        return trainerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getById(@PathVariable("id") Long id) {
        Trainer trainer = trainerService.getById(id);
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }

    @GetMapping("/{id}/bootcamps")
    public ResponseEntity<Set<Bootcamp>> getBootcamps(@PathVariable("id") Long id) {
        Trainer trainer = trainerService.getById(id);
        Set<Bootcamp> bootcamps = trainer.getBootcamps();
        return new ResponseEntity<>(bootcamps, HttpStatus.OK);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<Set<Bootcamp>> getSubjects(@PathVariable("id") Long id) {
        Trainer trainer = trainerService.getById(id);
        Set<Bootcamp> bootcamps = trainer.getBootcamps();
        return new ResponseEntity<>(bootcamps, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Trainer> save(@RequestBody Trainer o) {
        Trainer trainer = trainerService.insert(o);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("DAOUser", "/api/trainers/" + trainer.getId());
        return new ResponseEntity<>(trainer, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Trainer> update(@PathVariable("id") Long id, @RequestBody Trainer p){
        Trainer trainer = trainerService.update(id, p);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("DAOUser", "/api/trainers/" + trainer.getId());
        return new ResponseEntity<>(trainer, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Trainer> delete(@PathVariable("id") Long id) {
        Trainer trainer = trainerService.delete(id);
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }
}
