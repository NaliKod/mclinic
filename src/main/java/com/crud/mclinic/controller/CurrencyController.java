package com.crud.mclinic.controller;

import com.crud.mclinic.domain.CurrencyRateDto;
import com.crud.mclinic.domain.PaymentDto;
import com.crud.mclinic.mapper.CurrencyMapper;
import com.crud.mclinic.mapper.PaymentMapper;
import com.crud.mclinic.nbp.client.NbpClient;
import com.crud.mclinic.service.PaymentDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CurrencyController {

    @Autowired
    private NbpClient nbpClient;

    @Autowired
    private CurrencyMapper currencyMapper;

    @Autowired
    private PaymentDbService paymentDbService;

    @Autowired
    private PaymentMapper paymentMapper;


    @GetMapping(value="/currencies/{currency}")
    public CurrencyRateDto getCurencyRate(@PathVariable String currency){
        return currencyMapper.mapToCurrencyRateDto(nbpClient.getRate(currency));
    }

    @GetMapping(value="/currencies/{currency}/visits/{visitId}")
    public PaymentDto getPaymentVisit(@PathVariable String currency,@PathVariable Long visitId){

       return paymentMapper.mapToPaymentDto(paymentDbService.createBasicVisitPayment
                (currencyMapper.mapToCurrencyRateDto(nbpClient.getRate(currency)).getRate(),visitId));
    }
}
