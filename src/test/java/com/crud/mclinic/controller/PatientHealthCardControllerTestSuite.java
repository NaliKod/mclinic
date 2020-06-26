package com.crud.mclinic.controller;

import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.PatientHealthCard;
import com.crud.mclinic.domain.PatientHealthCardDto;
import com.crud.mclinic.mapper.PatientHealthCardMapper;
import com.crud.mclinic.service.PatientHealthCardDbService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientHealthCardController.class)
public class PatientHealthCardControllerTestSuite {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientHealthCardDbService patientHealthCardDbService;

    @MockBean
    private PatientHealthCardMapper patientHealthCardMapper;

    @Test
    public void shouldGetAllPatientHealthCards() throws Exception {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientHealthCard patientHealthCard = PatientHealthCard.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient").patient(patient).build();
        PatientHealthCardDto patientHealthCardDto = PatientHealthCardDto.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient Dto").patientId(9L).build();
        List<PatientHealthCard> patientHealthCardsList = new ArrayList<>();
        patientHealthCardsList.add(patientHealthCard);
        List<PatientHealthCardDto> patientHealthCardDtoList = new ArrayList<>();
        patientHealthCardDtoList.add(patientHealthCardDto);
        when(patientHealthCardMapper.mapToPatientHealthCardsDtoList(patientHealthCardsList)).thenReturn(patientHealthCardDtoList);
        when(patientHealthCardDbService.getAllPatientHealthCard()).thenReturn(patientHealthCardsList);

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/patientHealthCards")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight").value("50.0 kg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].height").value("165.0 cm"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("New patient Dto"));
    }

    @Test
    public void shouldGetPatientHeathCardById() throws Exception {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientHealthCard patientHealthCard = PatientHealthCard.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient").patient(patient).build();
        PatientHealthCardDto patientHealthCardDto = PatientHealthCardDto.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient Dto").patientId(9L).build();
        when(patientHealthCardMapper.mapToPatientHealthCardDto(patientHealthCard)).thenReturn(patientHealthCardDto);
        when(patientHealthCardDbService.getPatientHealthCard(patient.getId())).thenReturn(patientHealthCard);

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/patientHealthCards/patients/9")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight").value("50.0 kg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.height").value("165.0 cm"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("New patient Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientId").value(9));
    }

    @Test
    public void shouldCreatePatientHealthCard() throws Exception {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientHealthCard patientHealthCard = PatientHealthCard.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient").patient(patient).build();
        PatientHealthCardDto patientHealthCardDto = PatientHealthCardDto.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient Dto").patientId(9L).build();
        when(patientHealthCardMapper.mapToPatientHealthCardDto(patientHealthCard)).thenReturn(patientHealthCardDto);

        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .post("/v1/patientHealthCards")
                .content(asJsonString(new PatientHealthCardDto(8L, "65.0 kg", "170 cm","Patient Dto",10L)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldUpdatePatientHealthCard() throws Exception {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientHealthCard patientHealthCard = PatientHealthCard.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient").patient(patient).build();
        PatientHealthCardDto patientHealthCardDto = PatientHealthCardDto.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient Dto").patientId(9L).build();
        when(patientHealthCardMapper.mapToPatientHealthCard(patientHealthCardDto)).thenReturn(patientHealthCard);
        when(patientHealthCardDbService.savePatientHealthCard(patientHealthCard)).thenReturn(patientHealthCard);
        when(patientHealthCardMapper.mapToPatientHealthCardDto(patientHealthCard)).thenReturn(patientHealthCardDto);

        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .put("/v1/patientHealthCards")
                .content(asJsonString(new PatientHealthCardDto(5L, "55.0 kg", "165 cm", "patient Dto", 9L)))
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
    public void shouldDeletePatientHealthCard() throws Exception {

        //When & Then
        mvc.perform(MockMvcRequestBuilders.delete("/v1/patientHealthCards/5"))
                .andExpect(status().isOk());
    }
}
