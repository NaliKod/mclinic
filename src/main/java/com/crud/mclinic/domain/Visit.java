package com.crud.mclinic.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "VISITS")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VISIT_ID", unique = true)
    private Long id;

    @Column(name = "DATE_VISIT")
    private LocalDate dateVisit;

    @Column(name = "TIME_VISIT")
    private LocalTime timeVisit;

    @Column(name = "IS_BOOKED")
    private boolean isBooked;

    @Column(name = "IS_CLOSED")
    private boolean isClosed;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

}
