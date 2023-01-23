package com.workshop.awscognitoidp.models;

import com.amazonaws.services.cognitoidp.model.AttributeType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class Student {
    private String username;
    private List<AttributeType> attributes;
}
