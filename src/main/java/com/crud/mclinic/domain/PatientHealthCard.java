package com.crud.mclinic.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "PATIENT_HEALTH_CARDS")
public class PatientHealthCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PATIENT_HEALTH_CARD_ID", unique = true)
    private Long id;

    @Column(name = "WEIGHT")
    private String weight;

    @Column(name = "HEIGHT")
    private String height;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "VISIT_ID")
    private Visit visit;
}
