package com.crud.mclinic.controller;

import com.crud.mclinic.domain.CurrencyRateDto;
import com.crud.mclinic.mapper.CurrencyMapper;
import com.crud.mclinic.nbp.client.NbpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name="/v1")
public class CurrencyController {

    @Autowired
    private NbpClient nbpClient;

    @Autowired
    CurrencyMapper currencyMapper;

    @GetMapping(value="/currencies/{currency}")
    public CurrencyRateDto getCurencyRate(@PathVariable String currency){
        return currencyMapper.mapToCurrencyRateDto(nbpClient.getRate(currency));
    }
}
