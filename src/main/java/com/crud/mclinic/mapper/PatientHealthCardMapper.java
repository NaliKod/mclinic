package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.PatientHealthCard;
import com.crud.mclinic.domain.PatientHealthCardDto;
import com.crud.mclinic.domain.Room;
import com.crud.mclinic.domain.RoomDto;
import com.crud.mclinic.service.PatientDbService;
import com.crud.mclinic.service.VisitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PatientHealthCardMapper {

    private PatientDbService patientDbService;

    @Autowired
    public PatientHealthCardMapper(PatientDbService patientDbService) {
        this.patientDbService = patientDbService;
    }

    public PatientHealthCard mapToPatientHealthCard(final PatientHealthCardDto patientHealthCardDto) {
        return PatientHealthCard.builder()
                .id(patientHealthCardDto.getId())
                .weight(patientHealthCardDto.getWeight())
                .height(patientHealthCardDto.getHeight())
                .description(patientHealthCardDto.getDescription())
                .patient(patientDbService.getPatientById(patientHealthCardDto.getPatientId()).get())
                .build();
    }

    public PatientHealthCardDto mapToPatientHealthCardDto(final PatientHealthCard patientHealthCard) {
        return PatientHealthCardDto.builder()
                .id(patientHealthCard.getId())
                .weight(patientHealthCard.getWeight())
                .height(patientHealthCard.getHeight())
                .description(patientHealthCard.getDescription())
                .patientId(patientHealthCard.getPatient().getId())
                .build();
    }

    public List<PatientHealthCardDto> mapToPatientHealthCardsDtoList(final List<PatientHealthCard> patientHealthCardsList) {
        return patientHealthCardsList.stream()
                .map(p -> PatientHealthCardDto.builder().id(p.getId()).weight(p.getWeight()).height(p.getHeight())
                        .description(p.getDescription())
                        .patientId(p.getPatient().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
