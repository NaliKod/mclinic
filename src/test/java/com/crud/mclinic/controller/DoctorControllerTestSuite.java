package com.crud.mclinic.controller;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.domain.DoctorDto;
import com.crud.mclinic.domain.Specialization;
import com.crud.mclinic.mapper.DoctorMapper;
import com.crud.mclinic.service.DoctorDbService;
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
@WebMvcTest(DoctorController.class)
public class DoctorControllerTestSuite {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DoctorDbService doctorDbService;

    @MockBean
    private DoctorMapper doctorMapper;

    @Test
    public void shouldGetDoctorBySpecialization() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        DoctorDto doctorDto = DoctorDto.builder().id(1L).name("Marina Dto").surname("Kowalski Dto").email("aaa@gmail.com Dto").specializationId(3L).build();

        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(doctor);
        List<DoctorDto> doctorDtoList = new ArrayList<>();
        doctorDtoList.add(doctorDto);
        when(doctorMapper.mapToDoctorDtoList(doctorList)).thenReturn(doctorDtoList);
        when(doctorDbService.getAllDoctorsWitSpecialization(specialization.getId())).thenReturn(doctorList);

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/doctors/specializations/3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Marina Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value("Kowalski Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("aaa@gmail.com Dto"));
    }

    @Test
    public void shouldGetDoctor() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        DoctorDto doctorDto = DoctorDto.builder().id(1L).name("Marina Dto").surname("Kowalski Dto").email("aaa@gmail.com Dto").specializationId(3L).build();
        when(doctorMapper.mapToDoctorDto(doctor)).thenReturn(doctorDto);
        when(doctorDbService.getDoctorById(doctor.getId())).thenReturn(Optional.ofNullable(doctor));

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/doctors/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Marina Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Kowalski Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("aaa@gmail.com Dto"));
    }

    @Test
    public void shouldCreateDoctor() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        DoctorDto doctorDto = DoctorDto.builder().id(1L).name("Marina Dto").surname("Kowalski Dto").email("aaa@gmail.com Dto").specializationId(3L).build();
        when(doctorMapper.mapToDoctorDto(doctor)).thenReturn(doctorDto);

        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .post("/v1/doctors")
                .content(asJsonString(new DoctorDto(1L, "Marina Dto", "Kowalski Dto", "aaa@gmail.com Dto", 3L)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateDoctor() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        DoctorDto doctorDto = DoctorDto.builder().id(1L).name("Marina Dto").surname("Kowalski Dto").email("aaa@gmail.com Dto").specializationId(3L).build();
        when(doctorMapper.mapToDoctorDto(doctor)).thenReturn(doctorDto);
        when(doctorMapper.mapToDoctorDto(doctor)).thenReturn(doctorDto);

        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .put("/v1/doctors")
                .content(asJsonString(new DoctorDto(1L, "Marina Dto dto", "Kowalski Dto", "aaa@gmail.com", 3L)))
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
        mvc.perform(MockMvcRequestBuilders.delete("/v1/doctors/1"))
                .andExpect(status().isOk());
    }
}
