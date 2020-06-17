package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.CurrencyDto;
import com.crud.mclinic.domain.CurrencyRateDto;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {

    public CurrencyRateDto mapToCurrencyRateDto(CurrencyDto currencyDto){
        return CurrencyRateDto.builder()
                .currency(currencyDto.getCode())
                .rate(currencyDto.getRates().get(0).getMid())
                .build();
    }
}
