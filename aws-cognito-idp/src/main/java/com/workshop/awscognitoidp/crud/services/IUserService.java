package com.workshop.awscognitoidp.crud.services;

import com.workshop.awscognitoidp.crud.models.UserDB;
import com.workshop.awscognitoidp.models.UserSignUpRequest;

public interface IUserService extends Service<UserDB> {
    UserDB insertUser(UserSignUpRequest user);
}
