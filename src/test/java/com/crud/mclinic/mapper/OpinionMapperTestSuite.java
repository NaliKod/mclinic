package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Opinion;
import com.crud.mclinic.domain.OpinionDto;
import com.crud.mclinic.domain.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class OpinionMapperTestSuite {

    @InjectMocks
    private OpinionMapper opinionMapper;

    @Test
    public void testMapToOpinion() {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        OpinionDto opinionDto = OpinionDto.builder().id(2L).description("I am very happy Dto").rate(10).patientId(9L).build();

        //When
        Opinion mapperOpinion = opinionMapper.mapToOpinion(opinionDto);
    }

    @Test
    public void testMapToOpinionDto() {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        Opinion opinion = Opinion.builder().id(2L).description("I am very happy").rate(10).patient(patient).build();

        //When
        OpinionDto mappedOpinion = opinionMapper.mapToOpinionDto(opinion);
        int rateOpinion = mappedOpinion.getRate();

        //Then
        assertNotNull(mappedOpinion);
        assertEquals("I am very happy", mappedOpinion.getDescription());
        assertEquals(10, rateOpinion);
    }

    @Test
    public void testMapToOpinionDtoList() {
        //Given
        Patient patient = Patient.builder()
                .id(9L).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        Opinion opinion = Opinion.builder().id(2L).description("I am very happy").rate(10).patient(patient).build();
        List<Opinion> opinions = new ArrayList<>();
        opinions.add(opinion);

        //When
        List<OpinionDto> mappedOpinionList = opinionMapper.mapToOpinionDtoList(opinions);
        int rateOpinion = mappedOpinionList.get(0).getRate();

        //Then
        assertNotNull(mappedOpinionList);
        assertEquals("I am very happy", mappedOpinionList.get(0).getDescription());
        assertEquals(10, rateOpinion);
    }
}
