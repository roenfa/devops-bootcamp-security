package com.workshop.awscognitoidp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Bootcamp>> getBootcamps(@PathVariable("id") Long id) {
        Trainer trainer = trainerService.getById(id);
        List<Bootcamp> bootcamps = trainer.getBootcamps();
        return new ResponseEntity<>(bootcamps, HttpStatus.OK);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<Bootcamp>> getSubjects(@PathVariable("id") Long id) {
        Trainer trainer = trainerService.getById(id);
        List<Bootcamp> bootcamps = trainer.getBootcamps();
        return new ResponseEntity<>(bootcamps, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Trainer> save(@RequestBody Trainer o) {
        Trainer trainer = trainerService.insert(o);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("DAOUser", "/api/trainers/" + trainer.getId());
        return new ResponseEntity<>(trainer, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainer> update(@PathVariable("id") Long id, @RequestBody Trainer p){
        Trainer trainer = trainerService.update(id, p);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("DAOUser", "/api/trainers/" + trainer.getId());
        return new ResponseEntity<>(trainer, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Trainer> delete(@PathVariable("id") Long id) {
        Trainer trainer = trainerService.delete(id);
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }
}
