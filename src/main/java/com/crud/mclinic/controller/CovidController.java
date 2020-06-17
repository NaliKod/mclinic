package com.crud.mclinic.controller;

import com.crud.mclinic.covid.client.CovidClient;
import com.crud.mclinic.domain.DataDto;
import com.crud.mclinic.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1")
public class CovidController {

    @Autowired
    private CovidClient covidClient;

    @Autowired
    private DataMapper dataMapper;

    @GetMapping(value="/covidActual/{country}")
    public DataDto getActual(@PathVariable String country){
        return dataMapper.mapToDataDto(covidClient.getStatistic(country));
    }
}
