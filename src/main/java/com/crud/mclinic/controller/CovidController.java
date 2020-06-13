package com.crud.mclinic.controller;

import com.crud.mclinic.covid.client.CovidClient;
import com.crud.mclinic.domain.CovidStatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CovidController {

    @Autowired
    private CovidClient covidClient;

    @GetMapping(value="/covidActual/{country}")
    public CovidStatDto getActual(String country){
        return covidClient.getStatistic(country);
    }
}
