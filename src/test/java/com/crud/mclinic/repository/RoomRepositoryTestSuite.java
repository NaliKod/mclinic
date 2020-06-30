package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.domain.Room;
import com.crud.mclinic.domain.Specialization;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomRepositoryTestSuite {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void testSaveRoom() {
        //Given
        Specialization specialization = Specialization.builder().id(null).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(null).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Room room = Room.builder().id(null).number(1).floor(1).doctor(doctor).build();

        //When
        roomRepository.save(room);
        Long roomId = room.getId();

        //Then
        Optional<Room> actualRoom = roomRepository.findById(roomId);
        Assert.assertTrue(actualRoom.isPresent());
        int roomNumber = actualRoom.get().getNumber();
        int roomFloor = actualRoom.get().getFloor();
        assertEquals(1,roomNumber);
        assertEquals(1,roomFloor);

        //CleanUp
        try {
            roomRepository.deleteById(roomId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
