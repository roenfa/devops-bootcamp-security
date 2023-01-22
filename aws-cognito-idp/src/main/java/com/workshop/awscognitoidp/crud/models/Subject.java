package com.workshop.awscognitoidp.crud.models;

// import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject extends AbstractEntity {
    
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    // @JoinColumn(name = "subject_id")
    // private Set<StudentGrade> studentsGrades;
    
    @ManyToOne
    private Trainer trainer;

    @Column
    private String name;
    private String description;

    @ManyToOne
    private Bootcamp bootcamp;

}
