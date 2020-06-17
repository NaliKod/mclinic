package com.crud.mclinic.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
@ToString
@Builder
@Entity(name="OPINIONS")
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Opinion_ID", unique = true)
    private Long id;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="RATE")
    private Integer rate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;
}
