package com.workshop.awscognitoidp.crud.models;

import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bootcamps")
public class Bootcamp extends AbstractEntity {

    @Column
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Subject> subjects;
}
