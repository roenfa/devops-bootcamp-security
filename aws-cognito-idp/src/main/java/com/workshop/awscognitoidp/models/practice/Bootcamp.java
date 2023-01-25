package com.workshop.awscognitoidp.models.practice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class Bootcamp {
    @Id
    private String nameBootcamp;
    private String admin;




}