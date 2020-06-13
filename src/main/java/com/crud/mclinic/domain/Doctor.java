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

    @OneToMany(
            targetEntity = Specialization.class,
            mappedBy = "doctor",
            cascade = CascadeType.ALL
           // fetch = FetchType.EAGER
    )
    private List<Specialization> specializations;

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
