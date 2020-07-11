package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class PrescriptionMapperTestSuite {

    @InjectMocks
    private PrescriptionMapper prescriptionMapper;

    @Test
    public void testMapToPrescriptionDto(){
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        LocalDate date = LocalDate.now();
        Prescription prescription = Prescription.builder().id(1L).drug("pencilin").chronicDisease(false).dosing("1 per day").duration(30).date(date)
                .doctor(doctor).patient(patient).build();

        //When
        PrescriptionDto mappedPrescription = prescriptionMapper.mapToPrescriptionDto(prescription);

        //Then
        assertNotNull(mappedPrescription);
        assertEquals("1 per day", mappedPrescription.getDosing());
        assertEquals("pencilin", mappedPrescription.getDrug());
        assertEquals(date, mappedPrescription.getDate());
    }

    @Test
    public void testMapToPrescription(){
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        LocalDate date = LocalDate.now();
        PrescriptionDto prescription = PrescriptionDto.builder().id(1L).drug("pencilin").chronicDisease(false).dosing("1 per day").duration(30).date(date)
                .doctorId(1L).patientId(9L).build();

        //When
        Prescription mappedPrescription = prescriptionMapper.mapToPrescription(prescription);

        //Then
        assertNotNull(mappedPrescription);
        assertEquals("1 per day", mappedPrescription.getDosing());
        assertEquals("pencilin", mappedPrescription.getDrug());
        assertEquals(date, mappedPrescription.getDate());
    }

    @Test
    public void testMapToPrescriptionDtoList(){
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        LocalDate date = LocalDate.now();
        Prescription prescription = Prescription.builder().id(1L).drug("pencilin").chronicDisease(false).dosing("1 per day").duration(30).date(date)
                .doctor(doctor).patient(patient).build();
        List<Prescription>prescriptions= new ArrayList<>();
        prescriptions.add(prescription);

        //When
        List<PrescriptionDto> mappedPrescriptionList = prescriptionMapper.mapToPrescriptionDtoList(prescriptions);

        //Then
        assertNotNull(mappedPrescriptionList);
        assertEquals("pencilin", mappedPrescriptionList.get(0).getDrug());
        assertEquals(date, mappedPrescriptionList.get(0).getDate());
        assertEquals("1 per day", mappedPrescriptionList.get(0).getDosing());
    }
}
