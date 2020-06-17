package com.crud.mclinic.controller;

import com.crud.mclinic.domain.DoctorDto;
import com.crud.mclinic.exceptions.DoctorNotFoundException;
import com.crud.mclinic.mapper.DoctorMapper;
import com.crud.mclinic.service.DoctorDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class DoctorController {

    private DoctorDbService doctorDbService;
    private DoctorMapper doctorMapper;

    @Autowired
    public DoctorController(DoctorDbService doctorDbService, DoctorMapper doctorMapper) {
        this.doctorDbService = doctorDbService;
        this.doctorMapper = doctorMapper;
    }

    @GetMapping(value = "/doctors/specializations/{specializationId}")
    public List<DoctorDto> getDoctors(@PathVariable Long specializationId) {
        return doctorMapper.mapToDoctorDtoList(doctorDbService.getAllDoctorsWitSpecialization(specializationId));
    }

    @GetMapping(value = "/doctors/{id}")
    public DoctorDto getDoctor(@PathVariable Long id) throws DoctorNotFoundException {
        return doctorMapper.mapToDoctorDto(doctorDbService.getDoctorById(id).orElseThrow(DoctorNotFoundException::new));
    }

    @DeleteMapping(value = "/doctors/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorDbService.deleteDoctorById(id);
    }

    @PutMapping(value = "/doctors")
    public DoctorDto updateDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorMapper.mapToDoctorDto(doctorDbService.saveDoctor(doctorMapper.mapToDoctor(doctorDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/doctors")
    public void createDoctor(@RequestBody DoctorDto doctorDto) {
        doctorDbService.saveDoctor(doctorMapper.mapToDoctor(doctorDto));
    }
}
