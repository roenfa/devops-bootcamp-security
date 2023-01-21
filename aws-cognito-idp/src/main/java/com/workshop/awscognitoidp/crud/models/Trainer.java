package com.workshop.awscognitoidp.crud.models;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainers")
public class Trainer extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "id")
    private UserDB user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "trainer_subjects",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
            )
    @LazyCollection(LazyCollectionOption.FALSE)
	private List<Subject> trainerSubjects;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "trainer_bootcamps",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "bootcamp_id")
            )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Bootcamp> bootcamps;
}