package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.PatientDto;
import com.crud.mclinic.domain.Room;
import com.crud.mclinic.domain.RoomDto;
import com.crud.mclinic.service.PrescriptionDbService;
import com.crud.mclinic.service.VisitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PatientMapper {

    private VisitDbService visitDbService;
    private PrescriptionDbService prescriptionDbService;

    @Autowired
    public PatientMapper(VisitDbService visitDbService, PrescriptionDbService prescriptionDbService) {
        this.visitDbService = visitDbService;
        this.prescriptionDbService = prescriptionDbService;
    }

    public Patient mapToPatient(final PatientDto patientDto) {
        return Patient.builder()
                .id(patientDto.getId())
                .name(patientDto.getName())
                .surname(patientDto.getSurname())
                .sex(patientDto.getSex())
                .pesel(patientDto.getPesel())
                .email(patientDto.getEmail())
                .address(patientDto.getAddress())
                .build();
    }

    public PatientDto mapToPatientDto(final Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .surname(patient.getSurname())
                .sex(patient.getSex())
                .pesel(patient.getPesel())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .build();
    }
    public List<PatientDto> mapToPatientDtoList(final List<Patient> patientsList) {
        return patientsList.stream()
                .map(p -> PatientDto.builder().id(p.getId()).name(p.getName()).surname(p.getSurname())
                        .sex(p.getSex()).pesel(p.getPesel())
                        .email(p.getEmail()).address(p.getAddress())
                        .build())
                .collect(Collectors.toList());
    }
}
