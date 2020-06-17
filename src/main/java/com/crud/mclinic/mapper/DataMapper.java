package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.CovidStatusMsgDto;
import com.crud.mclinic.domain.DataDto;
import org.springframework.stereotype.Component;

@Component
public class DataMapper {

    public DataDto mapToDataDto (final CovidStatusMsgDto covidStatusMsgDto){
        return DataDto.builder()
                .lastChecked(covidStatusMsgDto.getData().getLastChecked())
                .covid19Stats(covidStatusMsgDto.getData().getCovid19Stats())
                .build();
    }
}
