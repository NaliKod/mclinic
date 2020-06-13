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
@Entity(name="ROOMS")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROOM_ID", unique = true)
    private Long id;

    @Column(name="NUMBER")
    private Integer number;

    @Column(name="FLOOR")
    private Integer floor;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @OneToMany(
            targetEntity = Visit.class,
            mappedBy = "room",
            cascade = CascadeType.ALL
            //fetch = FetchType.EAGER
    )
    private List<Visit> visits;

}
