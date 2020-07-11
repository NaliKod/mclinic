package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.PatientDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class PatientMapperTestSuite {

    @InjectMocks
    private PatientMapper patientMapper;

    @Test
    public void testMapToPatient() {
        //Given
        PatientDto patientDto = PatientDto.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();

        //When
        Patient mappedPatient = patientMapper.mapToPatient(patientDto);

        //Then
        assertNotNull(mappedPatient);
        assertEquals("Anna", mappedPatient.getName());
        assertEquals("Grant", mappedPatient.getSurname());
        assertEquals("9999123", mappedPatient.getPesel());
        assertEquals("anna.grant@gmail.com", mappedPatient.getEmail());
        assertEquals("US New York", mappedPatient.getAddress());
    }

    @Test
    public void testMapToPatientDto() {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();

        //When
        PatientDto mappedPatient = patientMapper.mapToPatientDto(patient);

        //Then
        assertNotNull(mappedPatient);
        assertEquals("Anna", mappedPatient.getName());
        assertEquals("Grant", mappedPatient.getSurname());
        assertEquals("9999123", mappedPatient.getPesel());
        assertEquals("anna.grant@gmail.com", mappedPatient.getEmail());
        assertEquals("US New York", mappedPatient.getAddress());
    }

    @Test
    public void testMapToPatientDtoList() {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);

        //When
        List<PatientDto> mappedPatientList = patientMapper.mapToPatientDtoList(patients);

        //Then
        assertNotNull(mappedPatientList);
        assertEquals("Anna", mappedPatientList.get(0).getName());
        assertEquals("Grant", mappedPatientList.get(0).getSurname());
        assertEquals("9999123", mappedPatientList.get(0).getPesel());
        assertEquals("anna.grant@gmail.com", mappedPatientList.get(0).getEmail());
        assertEquals("US New York", mappedPatientList.get(0).getAddress());
    }
}
