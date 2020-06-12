package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Visit;
import com.crud.mclinic.domain.VisitDto;
import com.crud.mclinic.service.DoctorDbService;
import com.crud.mclinic.service.PatientDbService;
import com.crud.mclinic.service.RoomDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitMapper {

    private DoctorDbService doctorDbService;
    private PatientDbService patientDbService;
    private RoomDbService roomDbService;

    @Autowired
    public VisitMapper(DoctorDbService doctorDbService, PatientDbService patientDbService, RoomDbService roomDbService) {
        this.doctorDbService = doctorDbService;
        this.patientDbService = patientDbService;
        this.roomDbService = roomDbService;
    }

    public Visit mapToVisit(final VisitDto visitDto) {
        return Visit.builder()
                .id(visitDto.getId())
                .visitDate(visitDto.getVisitDate())
                .time(visitDto.getTime())
                .isPending(visitDto.isPending())
                .isConfirmed(visitDto.isConfirmed())
                .doctor(doctorDbService.getDoctorById(visitDto.getDoctorId()).get())
                .patient(patientDbService.getPatientById(visitDto.getPatientId()).get())
                .room(roomDbService.getRoomById(visitDto.getRoomId()).get())
                .build();
    }

    public VisitDto mapToVisitDto(final Visit visit) {
        return VisitDto.builder()
                .id(visit.getId())
                .visitDate(visit.getVisitDate())
                .isPending(visit.isPending())
                .isConfirmed(visit.isConfirmed())
                .doctorId(visit.getDoctor().getId())
                .patientId(visit.getPatient().getId())
                .roomId(visit.getRoom().getId())
                .build();
    }

    public List<Visit> mapToVisitList(final List<VisitDto> visitList) {
        return visitList.stream()
                .map(v -> Visit.builder().id(v.getId()).visitDate(v.getVisitDate()).isPending(v.isPending()).isConfirmed(v.isConfirmed())
                        .doctor(doctorDbService.getDoctorById(v.getPatientId()).get())
                        .patient(patientDbService.getPatientById(v.getPatientId()).get())
                        .room(roomDbService.getRoomById(v.getRoomId()).get())
                        .build())
                .collect(Collectors.toList());
    }

    public List<VisitDto> mapToVisitDtoList(final List<Visit> visitList) {
        return visitList.stream()
                .map(v -> VisitDto.builder().id(v.getId()).visitDate(v.getVisitDate()).isPending(v.isPending()).isConfirmed(v.isConfirmed())
                        .doctorId(v.getDoctor().getId())
                        .patientId(v.getPatient().getId())
                        .roomId(v.getRoom().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
