package com.crud.mclinic.controller;

import com.crud.mclinic.domain.*;
import com.crud.mclinic.mapper.PrescriptionMapper;
import com.crud.mclinic.service.PrescriptionDbService;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PrescriptionController.class)
public class PrescriptionControllerTestSuite {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PrescriptionDbService prescriptionDbService;

    @MockBean
    private PrescriptionMapper prescriptionMapper;


    @Test
    public void shouldGetAllPrescriptionByPatient() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        LocalDate date = LocalDate.now();
        Prescription prescription = Prescription.builder().id(1L).drug("pencilin").chronicDisease(false).dosing("1 per day").duration(30).date(date)
                .doctor(doctor).patient(patient).build();
        PrescriptionDto prescriptionDto = PrescriptionDto.builder().id(1L).drug("pencilin Dto").chronicDisease(false).dosing("1 per day Dto").duration(30).date(date).doctorId(1L).patientId(9L).build();
        List<Prescription> prescriptionsList = new ArrayList<>();
        prescriptionsList.add(prescription);
        List<PrescriptionDto> prescriptionDtoList = new ArrayList<>();
        prescriptionDtoList.add(prescriptionDto);
        when(prescriptionMapper.mapToPrescriptionDtoList(prescriptionsList)).thenReturn(prescriptionDtoList);
        when(prescriptionDbService.getPatientPrescriptions(patient.getId())).thenReturn(prescriptionsList);

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/prescriptions/patients/9")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drug").value("pencilin Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].chronicDisease").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dosing").value("1 per day Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].duration").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(LocalDate.of(2020, 6, 25).toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].doctorId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patientId").value(9L));
    }

    @Test
    public void shouldGetPrescriptionById() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        LocalDate date = LocalDate.now();
        Prescription prescription = Prescription.builder().id(1L).drug("pencilin").chronicDisease(false).dosing("1 per day").duration(30).date(date)
                .doctor(doctor).patient(patient).build();
        PrescriptionDto prescriptionDto = PrescriptionDto.builder().id(1L).drug("pencilin Dto").chronicDisease(false).dosing("1 per day Dto").duration(30).date(date).doctorId(1L).patientId(9L).build();
        when(prescriptionMapper.mapToPrescriptionDto(prescription)).thenReturn(prescriptionDto);
        when(prescriptionDbService.getPrescriptionById(prescription.getId())).thenReturn(Optional.ofNullable(prescription));

        //when & Then
        mvc.perform(MockMvcRequestBuilders
                .get("/v1/prescriptions/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drug").value("pencilin Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.chronicDisease").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dosing").value("1 per day Dto"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value(LocalDate.of(2020, 6, 25).toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doctorId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientId").value(9L));
    }

   /* @Test
    public void shouldCreatePrescription() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        LocalDate date = LocalDate.now();
        Prescription prescription = Prescription.builder().id(1L).drug("pencilin").chronicDisease(false).dosing("1 per day").duration(30).date(date)
                .doctor(doctor).patient(patient).build();
        PrescriptionDto prescriptionDto = PrescriptionDto.builder().id(1L).drug("pencilin Dto").chronicDisease(false).dosing("1 per day Dto").duration(30).date(date).doctorId(1L).patientId(9L).build();
        when(prescriptionMapper.mapToPrescriptionDto(prescription)).thenReturn(prescriptionDto);

        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .post("/v1/prescriptions")
                .content(asJsonString(new PrescriptionDto(1L, "pencilin Dto", false, "1 per day", 30, LocalDate.now(), 1L, 9L)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldUpdatePrescription() throws Exception {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        LocalDate date = LocalDate.now();
        Prescription prescription = Prescription.builder().id(1L).drug("pencilin").chronicDisease(false).dosing("1 per day").duration(30).date(date)
                .doctor(doctor).patient(patient).build();
        PrescriptionDto prescriptionDto = PrescriptionDto.builder().id(1L).drug("pencilin Dto").chronicDisease(false).dosing("1 per day Dto").duration(30).date(date).doctorId(1L).patientId(9L).build();
        when(prescriptionMapper.mapToPrescription(prescriptionDto)).thenReturn(prescription);
        when(prescriptionDbService.savePrescription(prescription)).thenReturn(prescription);
        when(prescriptionMapper.mapToPrescriptionDto(prescription)).thenReturn(prescriptionDto);

        //When & Then
        mvc.perform(MockMvcRequestBuilders
                .put("/v1/prescriptions")
                .content(asJsonString(new PrescriptionDto(1L, " Dto", false, "1 per day", 30, , 1L, 9L)))
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
*/
    @Test
    public void shouldDeletePrescription() throws Exception {

        //When & Then
        mvc.perform(MockMvcRequestBuilders.delete("/v1/prescriptions/5"))
                .andExpect(status().isOk());
    }
}
