package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Covid19StatDto;
import com.crud.mclinic.domain.CovidStatusMsgDto;
import org.springframework.stereotype.Component;


@Component
public class CovidStats19Mapper {

    public Covid19StatDto mapToCovidStat19Dto(final CovidStatusMsgDto covidStatusMsgDto) {
        return Covid19StatDto.builder()
                .city(covidStatusMsgDto.getData().getCovid19Stats().get(0).getCity())
                .province(covidStatusMsgDto.getData().getCovid19Stats().get(0).getProvince())
                .country(covidStatusMsgDto.getData().getCovid19Stats().get(0).getCountry())
                .keyId(covidStatusMsgDto.getData().getCovid19Stats().get(0).getKeyId())
                .lastUpdate(covidStatusMsgDto.getData().getCovid19Stats().get(0).getLastUpdate())
                .confirmed(covidStatusMsgDto.getData().getCovid19Stats().get(0).getConfirmed())
                .deaths(covidStatusMsgDto.getData().getCovid19Stats().get(0).getDeaths())
                .recovered(covidStatusMsgDto.getData().getCovid19Stats().get(0).getRecovered())
                .build();
    }
}
