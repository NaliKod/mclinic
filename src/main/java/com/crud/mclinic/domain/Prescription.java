package com.crud.mclinic.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name="PRESCRIPTIONS")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRESCRIPTION_ID", unique = true)
    private Long id;

    @Column(name="DRUG")
    private String drug;

    @Column(name="CHRONIC_DISEASE")
    private boolean chronicDisease;

    @Column(name="DOSING")
    private String dosing;

    @Column(name="DURATION")
    private Integer duration;

    @Column(name="DATE")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;
}
