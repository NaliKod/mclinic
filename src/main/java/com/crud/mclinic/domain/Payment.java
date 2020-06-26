package com.crud.mclinic.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name="PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_ID", unique = true)
    private Long id;

    @Column(name="COST")
    private BigDecimal cost;

    @Column(name="DESCRIPTION")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "VISIT_ID")
    private Visit visit;
}
