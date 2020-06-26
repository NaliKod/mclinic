package com.crud.mclinic.domain;

import lombok.*;


import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class PaymentDto {

    private Long id;
    private BigDecimal cost;
    private String description;
    private Long visitId;
}
