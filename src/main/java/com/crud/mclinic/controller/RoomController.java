package com.crud.mclinic.controller;

import com.crud.mclinic.domain.Room;
import com.crud.mclinic.domain.RoomDto;
import com.crud.mclinic.mapper.RoomMapper;
import com.crud.mclinic.service.RoomDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class RoomController {

    private RoomDbService roomDbService;
    private RoomMapper roomMapper;

    @Autowired
    public RoomController(RoomDbService roomDbService, RoomMapper roomMapper) {
        this.roomDbService = roomDbService;
        this.roomMapper = roomMapper;
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return new ArrayList<>();
    }

    @GetMapping("/rooms/{id}")
    public RoomDto getRoom(@PathVariable Long id) {
        return roomMapper.mapToRoomDto(roomDbService.getRoomById(id).get());
    }

    @DeleteMapping("/rooms")
    public void deleteRoom(@PathVariable Long id) {
        roomDbService.deleteRoomById(id);
    }

    @PutMapping(value = "/rooms")
    public RoomDto updateRoom(@RequestBody RoomDto roomDto) {
        return roomMapper.mapToRoomDto(roomDbService.saveRoom(roomMapper.mapToRoom(roomDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/rooms")
    public void createRoom(@RequestBody RoomDto roomDto) {
        roomDbService.saveRoom(roomMapper.mapToRoom(roomDto));
    }
}
