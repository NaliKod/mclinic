package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Visit;
import com.crud.mclinic.domain.VisitDto;
import com.crud.mclinic.service.DoctorDbService;
import com.crud.mclinic.service.PatientDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitMapper {

    private DoctorDbService doctorDbService;
    private PatientDbService patientDbService;

    @Autowired
    public VisitMapper(DoctorDbService doctorDbService, PatientDbService patientDbService) {
        this.doctorDbService = doctorDbService;
        this.patientDbService = patientDbService;
    }

    public Visit mapToVisit(final VisitDto visitDto) {
        return Visit.builder()
                .id(visitDto.getId())
                .dateTimeVisit(visitDto.getDateTimeVisit())
                .isBooked(visitDto.isBooked())
                .isClosed(visitDto.isClosed())
                .doctor(doctorDbService.getDoctorById(visitDto.getDoctorId()).get())
                .patient(patientDbService.getPatientById(visitDto.getPatientId()).get())
                .build();
    }

    public VisitDto mapToVisitDto(final Visit visit) {
        return VisitDto.builder()
                .id(visit.getId())
                .dateTimeVisit(visit.getDateTimeVisit())
                .isBooked(visit.isBooked())
                .isClosed(visit.isClosed())
                .doctorId(visit.getDoctor().getId())
                .patientId(visit.getPatient().getId())
                .build();
    }

    public List<Visit> mapToVisitList(final List<VisitDto> visitList) {
        return visitList.stream()
                .map(v -> Visit.builder().id(v.getId()).dateTimeVisit(v.getDateTimeVisit())
                        .isBooked(v.isBooked()).isClosed(v.isClosed())
                        .doctor(doctorDbService.getDoctorById(v.getDoctorId()).get())
                        .patient(patientDbService.getPatientById(v.getPatientId()).get())
                        .build())
                .collect(Collectors.toList());
    }

    public List<VisitDto> mapToVisitDtoList(final List<Visit> visitList) {
        return visitList.stream()
                .map(v -> VisitDto.builder().id(v.getId()).dateTimeVisit(v.getDateTimeVisit())
                        .isBooked(v.isBooked())
                        .isClosed(v.isClosed())
                        .doctorId(v.getDoctor().getId())
                        .patientId(v.getPatient().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
