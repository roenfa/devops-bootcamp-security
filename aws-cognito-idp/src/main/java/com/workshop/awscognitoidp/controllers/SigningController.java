package com.workshop.awscognitoidp.controllers;

import com.workshop.awscognitoidp.exceptions.CognitoUserException;
import com.workshop.awscognitoidp.models.*;
import com.workshop.awscognitoidp.services.UserAuthenticationService;
import com.workshop.awscognitoidp.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
public class SigningController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @PostMapping(path = "/sign-up")
    public void signUp(@RequestBody UserSignUpRequest userSignUpRequest) throws CognitoUserException {

        this.userRegistrationService.signUp(userSignUpRequest);
    }

    @PostMapping(path = "/sign-in")
    public @ResponseBody  UserSignInResponse signIn(@RequestBody UserSignInRequest userSignInRequest) throws CognitoUserException {
        return userAuthenticationService.signIn(userSignInRequest);
    }
}
