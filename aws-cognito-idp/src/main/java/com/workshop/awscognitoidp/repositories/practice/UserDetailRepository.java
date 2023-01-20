package com.workshop.awscognitoidp.repositories.practice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.awscognitoidp.models.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
  
}
