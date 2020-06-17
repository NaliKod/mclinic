package com.crud.mclinic.nbp.client;


import com.crud.mclinic.domain.CurrencyDto;
import com.crud.mclinic.nbp.config.NbpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Optional.ofNullable;


@Component
public class NbpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbpClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NbpConfig nbpConfig;

    public CurrencyDto getRate(String currency) {

        URI url = UriComponentsBuilder.fromHttpUrl(nbpConfig.getNbpApiEndpoint()+currency)
                .build()
                .toUri();
        try {
            CurrencyDto currencyResponse = restTemplate.getForObject(url, CurrencyDto.class);
            return ofNullable(currencyResponse).orElse(new CurrencyDto());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new CurrencyDto();
        }
    }
}
