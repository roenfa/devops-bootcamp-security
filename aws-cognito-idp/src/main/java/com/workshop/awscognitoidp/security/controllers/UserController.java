package com.workshop.awscognitoidp.security.controllers;

import com.workshop.awscognitoidp.security.exceptions.CognitoUserException;
import com.workshop.awscognitoidp.security.models.*;
import com.workshop.awscognitoidp.security.service.UserAuthenticationService;
import com.workshop.awscognitoidp.security.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @PostMapping(path = "/sign-up")
    public void signUp(@RequestBody UserSignUpRequest userSignUpRequest) throws CognitoUserException {
        this.userRegistrationService.signUp(userSignUpRequest, UserRole.STUDENT);
    }

    @PostMapping(path = "/trainer/sign-up")
    public void signUpTrainer(@RequestBody UserSignUpRequest userSignUpRequest) throws CognitoUserException {
        this.userRegistrationService.signUp(userSignUpRequest, UserRole.TRAINER);
    }

    @PostMapping(path = "/sign-in")
    public @ResponseBody UserSignInResponse signIn(@RequestBody UserSignInRequest userSignInRequest) throws CognitoUserException {
        return userAuthenticationService.signIn(userSignInRequest);
    }

    @GetMapping(path = "/detail")
    public @ResponseBody UserDetail getUserDetail() {

        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName("Test");
        userDetail.setLastName("Buddy");
        userDetail.setEmail("testbuddy@tutotialsbuddy.com");
        return userDetail;
    }
}
