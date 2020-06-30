package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.PatientHealthCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientHealthCardRepositoryTestSuite {

    @Autowired
    PatientHealthCardRepository patientHealthCardRepository;

    @Autowired
    PatientRepository patientRepository;

    @Test
    public void testSavePatientHealthCard() {
        //Given
        Patient patient = Patient.builder()
                .name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        PatientHealthCard patientHealthCard = PatientHealthCard.builder().weight("50.0 kg").height("165.0 cm").description("New patient").patient(patient).build();

        //When
        patientHealthCardRepository.save(patientHealthCard);
        Long patientHealthCardId = patientHealthCard.getId();

        //Then
        Optional<PatientHealthCard> actualPatientHealthCard = patientHealthCardRepository.findById(patientHealthCardId);
        Assert.assertTrue(actualPatientHealthCard.isPresent());
        assertEquals("50.0 kg", actualPatientHealthCard.get().getWeight());
        assertEquals("165.0 cm", actualPatientHealthCard.get().getHeight());
        assertEquals("New patient", actualPatientHealthCard.get().getDescription());

        //CleanUp
        try {
            patientHealthCardRepository.deleteById(patientHealthCardId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
