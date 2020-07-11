package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.domain.Room;
import com.crud.mclinic.domain.RoomDto;
import com.crud.mclinic.domain.Specialization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class RoomMapperTestSuite {

    @InjectMocks
    private RoomMapper roomMapper;

    @Test
    public void testMapToRoom() {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        RoomDto roomDto = RoomDto.builder().id(1L).number(1).floor(1).doctorId(1L).build();

        //When
        Room mappedRoom = roomMapper.mapToRoom(roomDto);
        int number = mappedRoom.getNumber();
        int floor = mappedRoom.getFloor();

        //Then
        assertNotNull(mappedRoom);
        assertEquals(1, number);
        assertEquals(1, floor);
    }

    @Test
    public void testMapToRoomDto() {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Room room = Room.builder().id(1L).number(1).floor(1).doctor(doctor).build();

        //When
        RoomDto mappedRoom = roomMapper.mapToRoomDto(room);
        int number = mappedRoom.getNumber();
        int floor = mappedRoom.getFloor();

        //Then
        assertNotNull(mappedRoom);
        assertEquals(1, number);
        assertEquals(1, floor);
    }

    @Test
    public void testMapToRoomDtoList() {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Room room = Room.builder().id(1L).number(1).floor(1).doctor(doctor).build();
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        //When
        List<RoomDto> mappedRoomList = roomMapper.mapToRoomDtoList(rooms);
        int number = mappedRoomList.get(0).getNumber();
        int floor = mappedRoomList.get(0).getFloor();

        //Then
        assertNotNull(mappedRoomList);
        assertEquals(1, number);
        assertEquals(1, floor);
    }
}
