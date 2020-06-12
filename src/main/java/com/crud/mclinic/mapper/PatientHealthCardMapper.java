package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.PatientHealthCard;
import com.crud.mclinic.domain.PatientHealthCardDto;
import com.crud.mclinic.service.PatientDbService;
import com.crud.mclinic.service.VisitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PatientHealthCardMapper {

    private PatientDbService patientDbService;
    private VisitDbService visitDbService;

    @Autowired
    public PatientHealthCardMapper(PatientDbService patientDbService, VisitDbService visitDbService) {
        this.patientDbService = patientDbService;
        this.visitDbService = visitDbService;
    }

    public PatientHealthCard mapToPatientHealthCard(final PatientHealthCardDto patientHealthCardDto) {
        return PatientHealthCard.builder()
                .id(patientHealthCardDto.getId())
                .weight(patientHealthCardDto.getWeight())
                .height(patientHealthCardDto.getHeight())
                .description(patientHealthCardDto.getDescription())
                .patient(patientDbService.getPatientById(patientHealthCardDto.getId()).get())
                .visit(visitDbService.getVisitById(patientHealthCardDto.getVisitId()).get())
                .build();
    }

    public PatientHealthCardDto mapToPatientHealthCardDto(final PatientHealthCard patientHealthCard) {
        return PatientHealthCardDto.builder()
                .id(patientHealthCard.getId())
                .weight(patientHealthCard.getWeight())
                .height(patientHealthCard.getHeight())
                .description(patientHealthCard.getDescription())
                .patientId(patientHealthCard.getPatient().getId())
                .visitId(patientHealthCard.getVisit().getId())
                .build();
    }
}
