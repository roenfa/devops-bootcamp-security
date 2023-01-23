package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import com.workshop.awscognitoidp.models.UserDetail;

public interface UserDetailService {
  UserDetail saveUserDetail(UserDetail userDetail);
  Optional<UserDetail> getUserDetailById(Long idUserDetail);
  boolean deleteUserDetailById(Long idUserDetail);
  Iterable<UserDetail> findAll();
  Optional<UserDetail> findUserDetailByUsername(String username);
}
