package com.crud.mclinic.controller;

import com.crud.mclinic.domain.*;
import com.crud.mclinic.mapper.PrescriptionMapper;
import com.crud.mclinic.mapper.RoomMapper;
import com.crud.mclinic.service.PrescriptionDbService;
import com.crud.mclinic.service.RoomDbService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
public class RoomControllerTestSuite {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomDbService roomDbService;

    @MockBean
    private RoomMapper roomMapper;

    @Test
    public void shouldGetAllRooms() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Room room = Room.builder().id(1L).number(1).floor(1).doctor(doctor).build();
        RoomDto roomDto = RoomDto.builder().id(1L).number(1).floor(1).doctorId(1L).build();
        List<Room> roomsList = new ArrayList<>();
        roomsList.add(room);
        List<RoomDto> roomDtoList = new ArrayList<>();
        roomDtoList.add(roomDto);
        when(roomMapper.mapToRoomDtoList(roomsList)).thenReturn(roomDtoList);
        when(roomDbService.getAllRooms()).thenReturn(roomsList);

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/rooms")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].number").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].floor").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].doctorId").value(1));
    }

    @Test
    public void shouldGetRoom() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Room room = Room.builder().id(1L).number(1).floor(1).doctor(doctor).build();
        RoomDto roomDto = RoomDto.builder().id(1L).number(1).floor(1).doctorId(1L).build();
        when(roomMapper.mapToRoomDto(room)).thenReturn(roomDto);
        when(roomDbService.getRoomById(room.getId())).thenReturn(Optional.ofNullable(room));

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/rooms/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.floor").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doctorId").value(1));
    }

    @Test
    public void shouldCreateRoom() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Room room = Room.builder().id(1L).number(1).floor(1).doctor(doctor).build();
        RoomDto roomDto = RoomDto.builder().id(1L).number(1).floor(1).doctorId(1L).build();
        when(roomMapper.mapToRoomDto(room)).thenReturn(roomDto);

        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .post("/v1/rooms")
                .content(asJsonString(new RoomDto(1L, 1,1,1L)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldDeleteOpinion() throws Exception {

        //When & Then
        mvc.perform(MockMvcRequestBuilders.delete("/v1/rooms/1"))
                .andExpect(status().isOk());
    }
}
