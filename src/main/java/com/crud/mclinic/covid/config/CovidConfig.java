package com.crud.mclinic.covid.config;

import com.crud.mclinic.domain.CovidStatDto;
import com.crud.mclinic.domain.CurrencyDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Optional.ofNullable;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Component
@Getter
public class CovidConfig {

    @Value("${covid.api.endpoint.prod}")
    private String covidAppiEndpoint;
}
