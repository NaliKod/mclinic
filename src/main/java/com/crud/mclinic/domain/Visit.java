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

    @Column(name = "VISIT_DATE")
    private LocalDate visitDate;

    @Column(name = "TIME")
    private LocalTime time;

    @Column(name = "IS_PENDING")
    private boolean isPending;

    @Column(name = "IS_CONFIRMED")
    boolean isConfirmed;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

}
