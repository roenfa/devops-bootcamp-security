package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.workshop.awscognitoidp.models.UserDetail;
import com.workshop.awscognitoidp.repositories.practice.UserDetailRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

  private final UserDetailRepository userDetailRepository;

  @Override
  public UserDetail saveUserDetail(UserDetail userDetail) {
    return userDetailRepository.save(userDetail);
  }

  @Override
  public Optional<UserDetail> getUserDetailById(Long idUserDetail) {
    return userDetailRepository.findById(idUserDetail);
  }

  @Override
  public boolean deleteUserDetailById(Long idUserDetail) {
    try {
      userDetailRepository.deleteById(idUserDetail);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
}
