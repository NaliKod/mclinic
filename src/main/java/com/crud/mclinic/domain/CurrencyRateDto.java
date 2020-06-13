package com.crud.mclinic.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurrencyRateDto {

    private String currency;
    private double rate;
}
