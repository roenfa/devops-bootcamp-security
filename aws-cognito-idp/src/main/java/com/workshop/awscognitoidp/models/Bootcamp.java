package com.workshop.awscognitoidp.models;

import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Random;


@Getter
public enum Bootcamp {

    DEVOPS(List.of(
            Subject.builder().name("Continuous Deployment").build(),
            Subject.builder().name("Project Monitoring").build(),
            Subject.builder().name("Ping pong").build()
    )),

    QA(List.of(
            Subject.builder().name("Selenium").build(),
            Subject.builder().name("Black Box Testing").build(),
            Subject.builder().name("TDD").build()
    )),

    FULLSTACK(List.of(
            Subject.builder().name("Python").build(),
            Subject.builder().name("React").build(),
            Subject.builder().name("Angular").build(),
            Subject.builder().name("Cooking").build()
    ));

    private List<Subject> subjects;

    private Bootcamp( List<Subject> subjects) {
        this.subjects = subjects;
    }

    public static Bootcamp getRandomBootcamp() {
        Random random = new Random();
        return Bootcamp.values()[random.nextInt(Bootcamp.values().length)];
    }

}
