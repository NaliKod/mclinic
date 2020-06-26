package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.domain.DoctorDto;
import com.crud.mclinic.domain.Opinion;
import com.crud.mclinic.domain.OpinionDto;
import com.crud.mclinic.service.PatientDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<OpinionDto> mapToOpinionDtoList(final List<Opinion> opinionsList) {
        return opinionsList.stream()
                .map(o -> OpinionDto.builder().id(o.getId()).description(o.getDescription()).rate(o.getRate())
                        .patientId(o.getId())
                        .build())
                .collect(Collectors.toList());
    }
}