package com.workshop.awscognitoidp.models;

import com.amazonaws.services.cognitoidp.model.AttributeType;
import lombok.Data;

import java.util.List;

@Data
public class Trainer {
    private String username;
    private List<AttributeType> attributes;
}
