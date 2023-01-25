package com.workshop.awscognitoidp.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Subject {

    private String name;

    @Builder.Default
    private Integer score = 0;

}
