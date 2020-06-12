package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Prescription;
import com.crud.mclinic.domain.PrescriptionDto;
import com.crud.mclinic.service.DoctorDbService;
import com.crud.mclinic.service.PatientDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PrescriptionMapper {

    private DoctorDbService doctorDbService;
    private PatientDbService patientDbService;

    @Autowired
    public PrescriptionMapper(DoctorDbService doctorDbService, PatientDbService patientDbService) {
        this.doctorDbService = doctorDbService;
        this.patientDbService = patientDbService;
    }

    public Prescription mapToPrescription(final PrescriptionDto prescriptionDto) {
        return Prescription.builder()
                .id(prescriptionDto.getId())
                .drug(prescriptionDto.getDrug())
                .chronicDisease(prescriptionDto.isChronicDisease())
                .dosing(prescriptionDto.getDosing())
                .duration(prescriptionDto.getDuration())
                .date(prescriptionDto.getDate())
                .doctor(doctorDbService.getDoctorById(prescriptionDto.getDoctorId()).get())
                .patient(patientDbService.getPatientById(prescriptionDto.getPatientId()).get())
                .build();
    }

    public PrescriptionDto mapToPrescriptionDto(final Prescription prescription) {
        return PrescriptionDto.builder()
                .id(prescription.getId())
                .drug(prescription.getDrug())
                .chronicDisease(prescription.isChronicDisease())
                .dosing(prescription.getDosing())
                .duration(prescription.getDuration())
                .date(prescription.getDate())
                .doctorId(prescription.getDoctor().getId())
                .patientId(prescription.getPatient().getId())
                .build();
    }
}
