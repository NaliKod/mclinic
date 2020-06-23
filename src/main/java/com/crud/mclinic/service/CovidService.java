package com.crud.mclinic.service;

import com.crud.mclinic.config.AdminConfig;
import com.crud.mclinic.covid.client.CovidClient;
import com.crud.mclinic.domain.CovidStatusMsgDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CovidService {

    @Autowired
    private CovidClient covidClient;

    @Autowired
    private ScheduledPatientEmailService mailService;

    @Autowired
    private AdminConfig adminConfig;

    public CovidStatusMsgDto getStatistic(String country){

        return covidClient.getStatistic(country);
    }
}
