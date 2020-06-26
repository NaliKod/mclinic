package com.crud.mclinic.controller;

import com.crud.mclinic.domain.Opinion;
import com.crud.mclinic.domain.OpinionDto;
import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.PatientDto;
import com.crud.mclinic.mapper.OpinionMapper;
import com.crud.mclinic.service.OpinionDbService;
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
@WebMvcTest(OpinionController.class)
public class OpinionControllerTestSuite {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private OpinionDbService opinionDbService;

    @MockBean
    private OpinionMapper opinionMapper;

    @Test
    public void shouldGetAllOpinions() throws Exception {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        Opinion opinion = Opinion.builder().id(2L).description("I am ver happy").rate(10).patient(patient).build();
        OpinionDto opinionDto = OpinionDto.builder().id(2L).description("I am very happy Dto").rate(10).patientId(9L).build();
        List<Opinion> opinionList = new ArrayList<>();
        opinionList.add(opinion);
        List<OpinionDto> opinionDtoList = new ArrayList<>();
        opinionDtoList.add(opinionDto);
        when(opinionMapper.mapToOpinionDtoList(opinionList)).thenReturn(opinionDtoList);
        when(opinionDbService.getAllOpinions()).thenReturn(opinionList);

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/opinions")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("I am very happy Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rate").value(10));
    }

    @Test
    public void shouldGetOpinion() throws Exception {
        //Given
            Patient patient = Patient.builder()
                    .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                    .email("anna.grant@gmail.com").address("US New York").build();
            Opinion opinion = Opinion.builder().id(2L).description("I am ver happy").rate(10).patient(patient).build();
            OpinionDto opinionDto = OpinionDto.builder().id(2L).description("I am very happy Dto").rate(10).patientId(9L).build();
        when(opinionMapper.mapToOpinionDto(opinion)).thenReturn(opinionDto);
        when(opinionDbService.getOpinionById(opinion.getId())).thenReturn(Optional.ofNullable(opinion));

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/opinions/2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("I am very happy Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(10));
    }

    @Test
    public void shouldCreateOpinion() throws Exception {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        Opinion opinion = Opinion.builder().id(2L).description("I am ver happy").rate(10).patient(patient).build();
        OpinionDto opinionDto = OpinionDto.builder().id(2L).description("I am ver happy").rate(10).patientId(9L).build();
        when(opinionMapper.mapToOpinionDto(opinion)).thenReturn(opinionDto);


        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .post("/v1/opinions")
                .content(asJsonString(new OpinionDto(8L, "I was good service",7,5L)))
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
        mvc.perform(MockMvcRequestBuilders.delete("/v1/opinions/1"))
                .andExpect(status().isOk());
    }
}
