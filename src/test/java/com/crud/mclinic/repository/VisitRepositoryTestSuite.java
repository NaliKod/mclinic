package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.Specialization;
import com.crud.mclinic.domain.Visit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitRepositoryTestSuite {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Test
    public void testBookVisit() {
        //Given
        Specialization specialization = Specialization.builder().id(null).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(null).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Patient patient = Patient.builder()
                .id(null).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        Visit visit = Visit.builder().id(null).dateTimeVisit(LocalDateTime.of(LocalDate.of(2020, 7, 3), LocalTime.of(15, 0, 0))).isBooked(false).isClosed(false).doctor(doctor).patient(patient).build();

        //When
        doctorRepository.save(doctor);
        Long doctorId = doctor.getId();
        patientRepository.save(patient);
        Long patientId = patient.getId();
        visitRepository.save(visit);
        Long visitId = visit.getId();

        //Then
        Optional<Visit> actualVisit = visitRepository.findById(visitId);
        Assert.assertTrue(actualVisit.isPresent());
        LocalDateTime visitTime = actualVisit.get().getDateTimeVisit();
        assertEquals(LocalDateTime.of(LocalDate.of(2020,7,3),LocalTime.of(15,0,0)), visitTime);
        assertFalse(actualVisit.get().isBooked());
        assertFalse(actualVisit.get().isClosed());

        //CleanUp
        try {
            patientRepository.deleteById(patientId);
            doctorRepository.deleteById(doctorId);
            visitRepository.deleteById(visitId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
