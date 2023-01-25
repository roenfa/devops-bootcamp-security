package com.workshop.awscognitoidp.services.practice;

import com.workshop.awscognitoidp.models.practice.User;
import com.workshop.awscognitoidp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsersByRole(String role) {
        return userRepository.getAllUsersByRole(role);
    }


    public User getUserByUsername(String id) {
        return userRepository.getUserByUsername(id);
    }

    public List<User>getAllUsersNotAssignedToCourse(String course){
        return userRepository.getAllUsersNotAssignedToCourse(course);
    }

}
