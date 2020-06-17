package com.crud.mclinic.covid.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CovidConfig {

    @Value("${covid.api.endpoint.prod}")
    private String covidAppiEndpoint;
}
