package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Opinion;
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
public class OpinionRepositoryTestSuite {

    @Autowired
    private OpinionRepository opinionRepository;

    @Test
    public void testSaveOpinion(){
        //Given
        Patient patient = Patient.builder()
                .id(null).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        Opinion opinion = Opinion.builder().id(null).description("I am very happy").rate(10).patient(patient).build();

        //When
        opinionRepository.save(opinion);
        Long opinionId = opinion.getId();

        //Then
        Optional<Opinion> actualOpinion = opinionRepository.findById(opinionId);
        Assert.assertTrue(actualOpinion.isPresent());
        int rate =actualOpinion.get().getRate();
        assertEquals("I am very happy", actualOpinion.get().getDescription());
        assertEquals(10,rate) ;
    }
}
