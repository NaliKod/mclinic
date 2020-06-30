package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.Prescription;
import com.crud.mclinic.domain.Specialization;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrescriptionRepositoryTestSuite {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testSavePrescription() {
        //Given
        Specialization specialization = Specialization.builder().id(null).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(null).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Patient patient = Patient.builder()
                .id(null).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        Prescription prescription = Prescription.builder().id(null).drug("pencilin").chronicDisease(false).dosing("1 per day").duration(30).date(LocalDate.now())
                .doctor(doctor).patient(patient).build();

        //When
        patientRepository.save(patient);
        Long patientId = patient.getId();
        doctorRepository.save(doctor);
        Long doctorId = doctor.getId();
        prescriptionRepository.save(prescription);
        Long prescriptionId = prescription.getId();

        //Then
        Optional<Prescription> actualPrescription = prescriptionRepository.findById(prescriptionId);
        Assert.assertTrue(actualPrescription.isPresent());
        assertEquals("pencilin", actualPrescription.get().getDrug());
        assertFalse(actualPrescription.get().isChronicDisease());
        assertEquals("1 per day", actualPrescription.get().getDosing());

        //CleanUp
        try {
            patientRepository.deleteById(patientId);
            doctorRepository.deleteById(doctorId);
            prescriptionRepository.deleteById(prescriptionId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
