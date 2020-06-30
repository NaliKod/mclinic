package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.domain.Specialization;
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
public class DoctorRepositoryTestSuite {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Test
    public void testSaveDoctor() {
        //Given
        Specialization specialization = Specialization.builder().name("laryngolog").build();
        Doctor doctor = Doctor.builder().name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();

        //When
        doctorRepository.save(doctor);
        Long doctorId = doctor.getId();

        //Then
        Optional<Doctor> actualDoctor = doctorRepository.findById(doctorId);
        Assert.assertTrue(actualDoctor.isPresent());
        assertEquals("Kowalski", actualDoctor.get().getSurname());
        assertEquals("Marina", actualDoctor.get().getName());
        assertEquals("laryngolog", actualDoctor.get().getSpecialization().getName());

        //CleanUp
        try {
            doctorRepository.deleteById(doctorId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
