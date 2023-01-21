package com.workshop.awscognitoidp.crud.models;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject extends AbstractEntity {
    @Column
    private String name;
    private String description;
}
