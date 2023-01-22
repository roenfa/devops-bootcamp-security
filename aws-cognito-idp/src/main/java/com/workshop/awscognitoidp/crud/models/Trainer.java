package com.workshop.awscognitoidp.crud.models;

import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainers")
public class Trainer extends AbstractEntity {

    @OneToOne
    private UserDB user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Subject> subjects;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "trainer_bootcamps",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "bootcamp_id")
            )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Bootcamp> bootcamps;
}