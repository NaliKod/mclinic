package com.crud.mclinic.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name="SPECIALIZATION")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SPECIALIZATION_ID", unique = true)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

}
