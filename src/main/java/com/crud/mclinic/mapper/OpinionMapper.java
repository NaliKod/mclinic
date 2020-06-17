package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Opinion;
import com.crud.mclinic.domain.OpinionDto;
import com.crud.mclinic.service.PatientDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpinionMapper {

    private PatientDbService patientDbService;

    @Autowired
    public OpinionMapper(PatientDbService patientDbService) {
        this.patientDbService = patientDbService;
    }

    public Opinion mapToOpinion(final OpinionDto opinionDto) {
        return Opinion.builder()
                .id(opinionDto.getId())
                .description(opinionDto.getDescription())
                .rate(opinionDto.getRate())
                .patient(patientDbService.getPatientById(opinionDto.getPatientId()).get())
                .build();
    }

    public OpinionDto mapToOpinionDto(final Opinion opinion) {
        return OpinionDto.builder()
                .id(opinion.getId())
                .description(opinion.getDescription())
                .rate(opinion.getRate())
                .patientId(opinion.getId())
                .build();
    }
}