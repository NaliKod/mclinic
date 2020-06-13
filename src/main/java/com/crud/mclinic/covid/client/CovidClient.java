package com.crud.mclinic.covid.client;

import com.crud.mclinic.covid.config.CovidConfig;
import com.crud.mclinic.domain.CovidStatDto;
import com.crud.mclinic.domain.CurrencyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Optional.ofNullable;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Component
public class CovidClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CovidClient.class);

    private RestTemplate restTemplate;
    private CovidConfig covidConfig;

    @Autowired
    public CovidClient(RestTemplate restTemplate, CovidConfig covidConfig) {
        this.restTemplate = restTemplate;
        this.covidConfig = covidConfig;
    }

    public CovidStatDto getStatistic(String country) {

        URI url = UriComponentsBuilder.fromHttpUrl(covidConfig.getCovidAppiEndpoint())
                .queryParam(country)
                .build()
                .toUri();
        try {
            CovidStatDto covidResponse = restTemplate.getForObject(url, CovidStatDto.class);
            return ofNullable(covidResponse).orElse(new CovidStatDto());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new CovidStatDto();
        }
    }
}
