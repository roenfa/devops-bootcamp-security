package com.workshop.awscognitoidp.crud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workshop.awscognitoidp.crud.models.UserDB;

@Repository
public interface UserRepository extends CrudRepository<UserDB, Long> {
    UserDB findByUsername(String username);
}
