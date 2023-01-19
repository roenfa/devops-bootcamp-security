package com.workshop.awscognitoidp.controllers;

import com.workshop.awscognitoidp.exceptions.CognitoUserException;
import com.workshop.awscognitoidp.models.UserDetail;
import com.workshop.awscognitoidp.models.UserSignInRequest;
import com.workshop.awscognitoidp.models.UserSignInResponse;
import com.workshop.awscognitoidp.models.UserSignUpRequest;
import com.workshop.awscognitoidp.services.UserAuthenticationService;
import com.workshop.awscognitoidp.services.UserRegistrationService;
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
    public String signUp(@RequestBody UserSignUpRequest userSignUpRequest) throws CognitoUserException {

        return userRegistrationService.signUp(userSignUpRequest);
    }

    @PostMapping(path = "/sign-in")
    public @ResponseBody  UserSignInResponse signIn(@RequestBody UserSignInRequest userSignInRequest) throws CognitoUserException {
        return userAuthenticationService.signIn(userSignInRequest);
    }

    @GetMapping(path = "/detail")
    public @ResponseBody  UserDetail getUserDetail() {

        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName("Test");
        userDetail.setLastName("Buddy");
        userDetail.setEmail("testbuddy@tutotialsbuddy.com");
        return userDetail;
    }
}
