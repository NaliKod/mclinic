package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Patient;
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
public class PatientRepositoryTestSuite {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testSavePatient() {
        //Given
        Patient patient = Patient.builder()
                .id(null).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();

        //When
        patientRepository.save(patient);
        Long patientId = patient.getId();

        //Then
        Optional<Patient> actualPatient = patientRepository.findById(patientId);
        Assert.assertTrue(actualPatient.isPresent());
        assertEquals("Grant", actualPatient.get().getSurname());
        assertEquals("Anna", actualPatient.get().getName());
        assertEquals("US New York", actualPatient.get().getAddress());

        //CleanUp
        try {
            patientRepository.deleteById(patientId);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testUpdatePatientData(){
        //Given
        Patient patient = Patient.builder()
                .id(null).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();

        //When
        patientRepository.save(patient);
        Long patientId = patient.getId();
        patient.setAddress("Madrid");
        patientRepository.save(patient);

        //Then
        Optional<Patient> actualPatient = patientRepository.findById(patientId);
        Assert.assertTrue(actualPatient.isPresent());
        assertEquals("Grant", actualPatient.get().getSurname());
        assertEquals("Anna", actualPatient.get().getName());
        assertEquals("Madrid", actualPatient.get().getAddress());

        //CleanUp
        try {
            patientRepository.deleteById(patientId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
