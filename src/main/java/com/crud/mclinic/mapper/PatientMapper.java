package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.PatientDto;
import com.crud.mclinic.service.PrescriptionDbService;
import com.crud.mclinic.service.VisitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
                .visits(visitDbService.getListOfVisits(patientDto.getVisitId()))
                .prescriptions(prescriptionDbService.getListOfPresciptions(patientDto.getPrescriptionId()))
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
                .visitId(visitDbService.getListOfLongIdVisits(patient.getVisits()))
                .prescriptionId(prescriptionDbService.getListOfLongIdPrescriptions(patient.getPrescriptions()))
                .build();
    }
}
