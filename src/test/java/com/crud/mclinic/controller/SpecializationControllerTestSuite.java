package com.crud.mclinic.controller;

import com.crud.mclinic.domain.*;
import com.crud.mclinic.mapper.SpecializationMapper;
import com.crud.mclinic.service.SpecializationDbService;
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
@WebMvcTest(SpecializationController.class)
public class SpecializationControllerTestSuite {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SpecializationDbService specializationDbService;

    @MockBean
    private SpecializationMapper specializationMapper;

    @Test
    public void shouldGetAllSpecialization() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        SpecializationDto specializationDto = SpecializationDto.builder().id(3L).name("laryngolog").build();

        List<Specialization> specializationsList = new ArrayList<>();
        specializationsList.add(specialization);
        List<SpecializationDto> specializationDtoList = new ArrayList<>();
        specializationDtoList.add(specializationDto);
        when(specializationMapper.mapToSpecializationDtoList(specializationsList)).thenReturn(specializationDtoList);
        when(specializationDbService.getAllSpecializations()).thenReturn(specializationsList);

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/specializations")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("laryngolog"));
    }

    @Test
    public void shouldGetSpecialization() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        SpecializationDto specializationDto = SpecializationDto.builder().id(3L).name("laryngolog").build();
        when(specializationMapper.mapToSpecializationDto(specialization)).thenReturn(specializationDto);
        when(specializationDbService.getSpecializationById(specialization.getId())).thenReturn(Optional.ofNullable(specialization));

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/specializations/3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("laryngolog"));
    }

    @Test
    public void shouldCreateSpecialization() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        SpecializationDto specializationDto = SpecializationDto.builder().id(3L).name("laryngolog").build();
        when(specializationMapper.mapToSpecializationDto(specialization)).thenReturn(specializationDto);

        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .post("/v1/specializations")
                .content(asJsonString(new SpecializationDto(2L, "ortopeda", "")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateSpecialization() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        SpecializationDto specializationDto = SpecializationDto.builder().id(3L).name("laryngolog").build();
        when(specializationMapper.mapToSpecializationDto(specialization)).thenReturn(specializationDto);
        when(specializationMapper.mapToSpecializationDto(specialization)).thenReturn(specializationDto);

        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .put("/v1/specializations")
                .content(asJsonString(new SpecializationDto(1L, "laryngolog","")))
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
    public void shouldDeleteSpecialization() throws Exception {

        //When & Then
        mvc.perform(MockMvcRequestBuilders.delete("/v1/specializations/1"))
                .andExpect(status().isOk());
    }
}
