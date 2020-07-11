package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.domain.DoctorDto;
import com.crud.mclinic.domain.Specialization;
import com.crud.mclinic.service.SpecializationDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DoctorMapperTestSuite {

    @InjectMocks
    private DoctorMapper doctorMapper;

    @MockBean
    private SpecializationDbService specializationDbService;


    @Test
    public void testMapToDoctor() {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        DoctorDto doctorDto = DoctorDto.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specializationId(3L).build();

        //When
        when(specializationDbService.getSpecializationById(doctorDto.getSpecializationId())).thenReturn(Optional.ofNullable(specialization));
       // Doctor mappedDoctor = doctorMapper.mapToDoctor(doctorDto);


        //Then
       /* assertNotNull(mappedDoctor);
        assertEquals("Marina", mappedDoctor.getName());
        assertEquals("Kowalski", mappedDoctor.getSurname());
        assertEquals("aaa@gmail.com", mappedDoctor.getEmail());*/
    }

    @Test
    public void testMapToDoctorDto() {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();

        //When
        DoctorDto mappedDoctor = doctorMapper.mapToDoctorDto(doctor);

        //Then
        assertNotNull(mappedDoctor);
        assertEquals("Marina", mappedDoctor.getName());
        assertEquals("Kowalski", mappedDoctor.getSurname());
        assertEquals("aaa@gmail.com", mappedDoctor.getEmail());
    }

    @Test
    public void testMapToDoctorDtoList() {
        //Given
        Specialization specialization = Specialization.builder().id(3L).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(1L).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);

        //When
        List<DoctorDto> mappedDoctorList = doctorMapper.mapToDoctorDtoList(doctors);

        //Then
        assertNotNull(mappedDoctorList);
        assertEquals("Marina", mappedDoctorList.get(0).getName());
        assertEquals("Kowalski", mappedDoctorList.get(0).getSurname());
        assertEquals("aaa@gmail.com", mappedDoctorList.get(0).getEmail());
    }
}
