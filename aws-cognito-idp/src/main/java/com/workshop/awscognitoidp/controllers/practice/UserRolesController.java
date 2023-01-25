package com.workshop.awscognitoidp.controllers.practice;

import com.workshop.awscognitoidp.models.practice.User;
import com.workshop.awscognitoidp.services.practice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user-roles")
public class UserRolesController {

    @Autowired
    private UserService userService;
    @GetMapping(path = "/getTrainers")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB')")
    public List<User> getTrainers() {
        return userService.getAllUsersByRole("TRAINER_AGALVISB");
    }

    @GetMapping(path = "/getStudents")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB')")
    public List<User> getStudents() {
        return userService.getAllUsersByRole("STUDENT_AGALVISB");
    }



    @PostMapping(path = "/getUser")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB') or hasAuthority('TRAINER_AGALVISB') or hasAuthority('STUDENT_AGALVISB')")
    public User getUser(@RequestBody String username) {
        System.out.println("username = " + username);
        return userService.getUserByUsername(username);
    }

    @GetMapping(path = "/getStudents/{course}")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB') or hasAuthority('TRAINER_AGALVISB')")
    public List<User> getAllUsersNotAssignedToCourse(@PathVariable String course) {
        return userService.getAllUsersNotAssignedToCourse(course);
    }


}
