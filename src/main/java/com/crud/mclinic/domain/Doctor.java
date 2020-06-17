package com.crud.mclinic.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name="DOCTORS")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DOCTOR_ID", unique = true)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="SURNAME")
     private String surname;

    @Column(name="EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "SPECIALIZATION_ID")
    private Specialization specialization;

    @OneToMany(
            targetEntity = Visit.class,
            mappedBy = "doctor",
            cascade = CascadeType.ALL
            //fetch = FetchType.EAGER
    )
    private List<Visit> visits;

    @OneToMany(
            targetEntity = Prescription.class,
            mappedBy = "doctor",
            cascade = CascadeType.ALL
            //fetch = FetchType.EAGER
    )
    private List<Prescription> prescriptions;

}
