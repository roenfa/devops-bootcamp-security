package com.workshop.awscognitoidp.security.config;

import java.util.Arrays;
import java.util.List;

public class ConfigurationConstants {
    public static List<String> permittedEndpointList = Arrays.asList("/api/users/sign-up", "/api/users/sign-in", "/api/users/trainer/sign-up");
}

