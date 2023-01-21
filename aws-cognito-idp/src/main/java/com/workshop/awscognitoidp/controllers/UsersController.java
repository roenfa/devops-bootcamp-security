package com.workshop.awscognitoidp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.workshop.awscognitoidp.crud.models.UserDB;
import com.workshop.awscognitoidp.crud.services.UserServiceImp;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    @Autowired
    private UserServiceImp userService;

    @GetMapping
    public List<UserDB> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDB> getById(@PathVariable("id") Long id) {
        UserDB user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDB> save(@RequestBody UserDB o) {
        UserDB user = userService.insert(o);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("UserDB", "/api/users/" + user.getId());
        return new ResponseEntity<>(user, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDB> update(@PathVariable("id") Long id, @RequestBody UserDB p){
        UserDB user = userService.update(id, p);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("UserDB", "/api/users/" + user.getId());
        return new ResponseEntity<>(user, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDB> delete(@PathVariable("id") Long id) {
        UserDB user = userService.delete(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

