package com.crud.mclinic.repository;

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
public class SpecializationRepositoryTestSuite {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Test
    public void testSaveSpecialization() {
        //Given
        Specialization specialization = Specialization.builder().id(null).name("laryngolog").build();

        //When
        specializationRepository.save(specialization);
        Long specializationId = specialization.getId();

        //Then
        Optional<Specialization> actualSpecialization = specializationRepository.findById(specializationId);
        Assert.assertTrue(actualSpecialization.isPresent());
        assertEquals("laryngolog", actualSpecialization.get().getName());

        //CleanUp
        try {
            specializationRepository.deleteById(specializationId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
