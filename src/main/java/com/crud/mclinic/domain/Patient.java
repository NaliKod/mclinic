package com.crud.mclinic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name="PATIENTS")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PATIENT_ID", unique = true)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="SURNAME")
    private String surname;

    @Column(name="SEX")
    private char sex;

    @Column(name="PESEL")
    private String pesel;

    @Column(name="EMAIL")
    private String email;

    @Column(name="ADDRESS")
    private String address;

    @OneToMany(
            targetEntity = Visit.class,
            mappedBy = "patient",
            cascade = CascadeType.ALL
            //fetch = FetchType.EAGER
    )
    private List<Visit> visits;

    @OneToMany(
            targetEntity = Prescription.class,
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Prescription> prescriptions;


}
