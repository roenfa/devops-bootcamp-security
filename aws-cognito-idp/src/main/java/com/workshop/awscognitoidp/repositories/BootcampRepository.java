package com.workshop.awscognitoidp.repositories;

import com.workshop.awscognitoidp.models.practice.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BootcampRepository extends JpaRepository<Bootcamp, String> {

}
