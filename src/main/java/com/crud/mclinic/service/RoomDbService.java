package com.crud.mclinic.service;

import com.crud.mclinic.domain.Room;
import com.crud.mclinic.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomDbService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomDbService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room findDoctorRoom(Long doctorId) {
        Room doctorRoom = new Room();
        for (Room actualRoom : getAllRooms()) {
            if (actualRoom.getDoctor().getId().equals(doctorId)) {
                doctorRoom = actualRoom;
                return doctorRoom;
            }
        }
        return doctorRoom;
    }
}
