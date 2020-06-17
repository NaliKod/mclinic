package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Room;
import com.crud.mclinic.domain.RoomDto;
import com.crud.mclinic.service.DoctorDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class RoomMapper {


    private DoctorDbService doctorDbService;

    @Autowired
    public RoomMapper(DoctorDbService doctorDbService) {
        this.doctorDbService = doctorDbService;
    }

    public Room mapToRoom(final RoomDto roomDto) {
        return Room.builder()
                .id(roomDto.getId())
                .number(roomDto.getNumber())
                .floor(roomDto.getFloor())
                .doctor(doctorDbService.getDoctorById(roomDto.getDoctorId()).get())
                .build();
    }

    public RoomDto mapToRoomDto(final Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .number(room.getNumber())
                .floor(room.getFloor())
                .doctorId(room.getDoctor().getId())
                .build();
    }

    public List<RoomDto> mapToRoomDtoList(final List<Room> roomsList) {
        return roomsList.stream()
                .map(r -> RoomDto.builder().id(r.getId()).number(r.getNumber()).floor(r.getFloor())
                        .doctorId(r.getDoctor().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
