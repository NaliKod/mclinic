package com.crud.mclinic.covid.client;

import com.crud.mclinic.covid.config.CovidConfig;
import com.crud.mclinic.domain.Covid19StatDto;
import com.crud.mclinic.domain.CovidStatusMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

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

    public CovidStatusMsgDto getStatistic(String country) {

        URI url = UriComponentsBuilder.fromHttpUrl(covidConfig.getCovidAppiEndpoint()+"?country="+country)
                .build()
                .toUri();
        try {
            CovidStatusMsgDto covidResponse = restTemplate.getForObject(url, CovidStatusMsgDto.class);
            return ofNullable(covidResponse).orElse(new CovidStatusMsgDto());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new CovidStatusMsgDto();
        }
    }
}
