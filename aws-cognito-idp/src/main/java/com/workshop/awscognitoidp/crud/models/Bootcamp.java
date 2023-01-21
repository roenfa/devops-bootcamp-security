package com.workshop.awscognitoidp.crud.models;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bootcamps")
public class Bootcamp extends AbstractEntity {
    @Column
    private String name;
    private String description;
}
