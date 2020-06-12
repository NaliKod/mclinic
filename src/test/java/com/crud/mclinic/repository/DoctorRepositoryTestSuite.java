package com.crud.mclinic.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorRepositoryTestSuite {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testSaveDoctor(){}


}
