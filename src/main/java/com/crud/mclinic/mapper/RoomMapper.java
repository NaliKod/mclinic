package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Room;
import com.crud.mclinic.domain.RoomDto;
import com.crud.mclinic.service.DoctorDbService;
import com.crud.mclinic.service.PatientDbService;
import com.crud.mclinic.service.VisitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RoomMapper {

    private PatientDbService patientDbService;
    private DoctorDbService doctorDbService;
    private VisitDbService visitDbService;

    @Autowired
    public RoomMapper(PatientDbService patientDbService, DoctorDbService doctorDbService, VisitDbService visitDbService) {
        this.patientDbService = patientDbService;
        this.doctorDbService = doctorDbService;
        this.visitDbService = visitDbService;
    }

    public Room mapToRoom(final RoomDto roomDto) {
        return Room.builder()
                .id(roomDto.getId())
                .number(roomDto.getNumber())
                .floor(roomDto.getFloor())
                .patient(patientDbService.getPatientById(roomDto.getPatientId()).get())
                .doctor(doctorDbService.getDoctorById(roomDto.getDoctorId()).get())
                .visits(visitDbService.getListOfVisits(roomDto.getVisitId()))
                .build();
    }

    public RoomDto mapToRoomDto(final Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .number(room.getNumber())
                .floor(room.getFloor())
                .patientId(room.getPatient().getId())
                .doctorId(room.getDoctor().getId())
                .visitId(visitDbService.getListOfLongIdVisits(room.getVisits()))
                .build();
    }
}
