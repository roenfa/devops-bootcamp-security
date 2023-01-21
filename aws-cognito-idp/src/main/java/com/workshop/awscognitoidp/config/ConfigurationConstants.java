package com.workshop.awscognitoidp.config;

import java.util.Arrays;
import java.util.List;

public class ConfigurationConstants {
    public static List<String> permitAllEndpointList = Arrays.asList("/api/auth/sign-up", "/api/auth/sign-in");
    public static List<String> secondTierEndpointList = Arrays.asList("/api/users");
    public static List<String> firstTierEndpointList = Arrays.asList("/api/trainers");
    public static List<String> allAuthEndpointList = Arrays.asList("/api/students");
}

