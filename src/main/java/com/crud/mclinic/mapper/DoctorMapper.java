package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.domain.DoctorDto;
import com.crud.mclinic.service.PrescriptionDbService;
import com.crud.mclinic.service.SpecializationDbService;
import com.crud.mclinic.service.VisitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class DoctorMapper {

    private VisitDbService visitDbService;
    private PrescriptionDbService prescriptionDbService;
    private SpecializationDbService specializationDbService;

    @Autowired
    public DoctorMapper(VisitDbService visitDbService, PrescriptionDbService prescriptionDbService, SpecializationDbService specializationDbService) {
        this.visitDbService = visitDbService;
        this.prescriptionDbService = prescriptionDbService;
        this.specializationDbService = specializationDbService;
    }

    public Doctor mapToDoctor(final DoctorDto doctorDto) {
        return Doctor.builder()
                .id(doctorDto.getId())
                .name(doctorDto.getName())
                .surname(doctorDto.getSurname())
                .specializations(specializationDbService.getListOfSpecializations(doctorDto.getSpecializationId()))
                .visits(visitDbService.getListOfVisits(doctorDto.getVisitId()))
                .prescriptions(prescriptionDbService.getListOfPresciptions(doctorDto.getVisitId()))
                .build();
    }

    public DoctorDto mapToDoctorDto(final Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .specializationId(specializationDbService.getListOfLongIdSpecializations(doctor.getSpecializations()))
                .visitId(visitDbService.getListOfLongIdVisits(doctor.getVisits()))
                .prescriptionId(prescriptionDbService.getListOfLongIdPrescriptions(doctor.getPrescriptions()))
                .build();
    }
    public List<DoctorDto> mapToDoctorDtoList(final List<Doctor> doctorList) {
        return doctorList.stream()
                .map(d -> DoctorDto.builder().id(d.getId()).name(d.getName()).surname(d.getSurname())
                        .specializationId(specializationDbService.getListOfLongIdSpecializations(d.getSpecializations()))
                        .visitId(visitDbService.getListOfLongIdVisits(d.getVisits()))
                        .prescriptionId(prescriptionDbService.getListOfLongIdPrescriptions(d.getPrescriptions()))
                        .build())
                .collect(Collectors.toList());
    }
}
