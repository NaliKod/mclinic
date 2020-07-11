package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.PatientHealthCard;
import com.crud.mclinic.domain.PatientHealthCardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class PatientHealthCardTestSuite {

    @InjectMocks
    private PatientHealthCardMapper patientHealthCardMapper;

    @Test
    public void testMapToPatientHealthCard() {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientHealthCard patientHealthCard = PatientHealthCard.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient").patient(patient).build();
        PatientHealthCardDto patientHealthCardDto = PatientHealthCardDto.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient Dto").patientId(9L).build();

        //When
        PatientHealthCard mappedPatientHealthCard = patientHealthCardMapper.mapToPatientHealthCard(patientHealthCardDto);

        //Then
        assertNotNull(mappedPatientHealthCard);
        assertEquals("New patient", mappedPatientHealthCard.getDescription());
        assertEquals("165.0 cm", mappedPatientHealthCard.getHeight());
        assertEquals("50.0 kg", mappedPatientHealthCard.getWeight());
    }

    @Test
    public void testMapToPatientHealthCardDto() {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientHealthCard patientHealthCard = PatientHealthCard.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient").patient(patient).build();
        PatientHealthCardDto patientHealthCardDto = PatientHealthCardDto.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient Dto").patientId(9L).build();

        //When
        PatientHealthCardDto mappedPatientHealthCard = patientHealthCardMapper.mapToPatientHealthCardDto(patientHealthCard);

        //Then
        assertNotNull(mappedPatientHealthCard);
        assertEquals("New patient", mappedPatientHealthCard.getDescription());
        assertEquals("165.0 cm", mappedPatientHealthCard.getHeight());
        assertEquals("50.0 kg", mappedPatientHealthCard.getWeight());
    }

    @Test
    public void testMapToPatientHealthCardDtoList() {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientHealthCard patientHealthCard = PatientHealthCard.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient").patient(patient).build();
        PatientHealthCardDto patientHealthCardDto = PatientHealthCardDto.builder().id(5L).weight("50.0 kg").height("165.0 cm").description("New patient Dto").patientId(9L).build();
        List<PatientHealthCard>patientHealthCardList = new ArrayList<>();
        patientHealthCardList.add(patientHealthCard);

        //When
        List<PatientHealthCardDto> mappedPatientHealthCardList = patientHealthCardMapper.mapToPatientHealthCardsDtoList(patientHealthCardList);

        //Then
        assertNotNull(mappedPatientHealthCardList);
        assertEquals("New patient", mappedPatientHealthCardList.get(0).getDescription());
        assertEquals("165.0 cm", mappedPatientHealthCardList.get(0).getHeight());
        assertEquals("50.0 kg", mappedPatientHealthCardList.get(0).getWeight());
    }
}
