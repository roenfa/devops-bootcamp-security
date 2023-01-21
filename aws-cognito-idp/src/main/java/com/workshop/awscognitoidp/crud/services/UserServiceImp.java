package com.workshop.awscognitoidp.crud.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import com.workshop.awscognitoidp.crud.models.UserDB;
import com.workshop.awscognitoidp.crud.repositories.UserRepository;
import com.workshop.awscognitoidp.models.UserSignUpRequest;

@org.springframework.stereotype.Service
public class UserServiceImp implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDB> getAll() {
        var it = userRepository.findAll();
        var users = new ArrayList<UserDB>();
        it.forEach(e -> {
            Hibernate.initialize(e.getRole());
            users.add(e);
        });

        return users;
    }

    @Override
    public UserDB getById(Long id) {
        UserDB user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public UserDB insert(UserDB user) {
        UserDB newUser = new UserDB();
		newUser.setUsername(user.getUsername());
		newUser.setRole(user.getRole());
		return userRepository.save(newUser);
    }

    @Override
    public UserDB insertUser(UserSignUpRequest user) {
        UserDB newUser = new UserDB();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
		newUser.setUsername(user.getUsername());
		newUser.setRole(user.getRole());
		return userRepository.save(newUser);
    }

    @Override
    public UserDB update(Long id, UserDB user) {
        UserDB userToUpdate = userRepository.findById(id).get();
        userToUpdate.setUsername(user.getUsername());
		userToUpdate.setRole(user.getRole());
        UserDB userUpdated = null;
        if (userToUpdate != null) {
            userUpdated = userRepository.save(userToUpdate);
         }

        return userUpdated;
    }

    @Override
    public UserDB delete(Long id) {
        UserDB user = userRepository.findById(id).get();
        
        if (user != null) {
            userRepository.delete(user);
        }

        return user;
    }

    // @Override
    // public User updateUser(Long id, User user) {
    //     User userToUpdate = userRepository.findById(id).get();
    //     userToUpdate.setUsername(user.getUsername());
    //     if (userToUpdate.getPassword().equals(user.getPassword())) {
    //         userToUpdate.setPassword(user.getPassword());
    //     } else {
    //         userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
    //     }
	// 	userToUpdate.setRoles(user.getRoles());
    //     User userUpdated = null;
    //     if (userToUpdate != null) {
    //         userUpdated = userRepository.save(userToUpdate);
    //      }

    //     return userUpdated;
    // }
}
