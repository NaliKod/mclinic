package com.crud.mclinic.controller;

import com.crud.mclinic.domain.*;
import com.crud.mclinic.mapper.PatientMapper;
import com.crud.mclinic.service.PatientDbService;
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
@WebMvcTest(PatientController.class)
public class PatientControllerTestSuite {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientDbService patientDbService;

    @MockBean
    private PatientMapper patientMapper;

    @Test
    public void shouldGetAllPatients() throws Exception {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientDto patientDto = PatientDto.builder()
                .id(9L).name("Anna Dto").surname("Grant Dto").sex('K').pesel("9999123 Dto")
                .email("anna.grant@gmail.com").address("US New York").build();

        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        List<PatientDto> patientDtoList = new ArrayList<>();
        patientDtoList.add(patientDto);
        when(patientMapper.mapToPatientDtoList(patientList)).thenReturn(patientDtoList);
        when(patientDbService.getAllPatients()).thenReturn(patientList);

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/patients")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Anna Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value("Grant Dto"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].sex").value('K'))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pesel").value("9999123 Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("anna.grant@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("US New York"));
    }

    @Test
    public void shouldGetPatient() throws Exception {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientDto patientDto = PatientDto.builder()
                .id(9L).name("Anna Dto").surname("Grant Dto").sex('K').pesel("9999123 Dto")
                .email("anna.grant@gmail.com").address("US New York").build();
        when(patientMapper.mapToPatientDto(patient)).thenReturn(patientDto);
        when(patientDbService.getPatientById(patient.getId())).thenReturn(Optional.ofNullable(patient));

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/patients/9")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Anna Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Grant Dto"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.sex").value('K'))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel").value("9999123 Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("anna.grant@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("US New York"));
    }

    @Test
    public void shouldCreatePatient() throws Exception {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientDto patientDto = PatientDto.builder()
                .id(9L).name("Anna Dto").surname("Grant Dto").sex('K').pesel("9999123 Dto")
                .email("anna.grant@gmail.com").address("US New York").build();
        when(patientMapper.mapToPatientDto(patient)).thenReturn(patientDto);


        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .post("/v1/patients")
                .content(asJsonString(new PatientDto(8L, "Olaf Dto", "Kowalski Dto", 'M', "999999999999", "aaa@gmail.com Dto", "UK London")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldUpdatePatient() throws Exception {
        //Given
            Patient patient = Patient.builder()
                    .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                    .email("anna.grant@gmail.com").address("US New York").build();
            PatientDto patientDto = PatientDto.builder()
                    .id(9L).name("Anna Dto").surname("Grant Dto").sex('K').pesel("9999123 Dto")
                    .email("anna.grant@gmail.com").address("US New York").build();

        when(patientMapper.mapToPatientDto(patient)).thenReturn(patientDto);
        when(patientDbService.savePatient(patient)).thenReturn(patient);
        when(patientMapper.mapToPatient(patientDto)).thenReturn(patient);

        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .put("/v1/patients")
                .content(asJsonString(new PatientDto(8L, "Olaf James Dto", "Kowalski Dto", 'M', "999999999999", "aaa@gmail.com Dto", "UK London")))
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
    public void shouldDeleteDoctor() throws Exception {

        //When & Then
        mvc.perform(MockMvcRequestBuilders.delete("/v1/patients/1"))
                .andExpect(status().isOk());
    }
}
