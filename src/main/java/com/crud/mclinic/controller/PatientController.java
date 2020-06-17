package com.crud.mclinic.controller;

import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.PatientDto;
import com.crud.mclinic.exceptions.PatientNotFoundException;
import com.crud.mclinic.factory.Treatment;
import com.crud.mclinic.factory.TreatmentFactory;
import com.crud.mclinic.mapper.PatientMapper;
import com.crud.mclinic.service.PatientDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class PatientController {

    private PatientDbService patientDbService;
    private PatientMapper patientMapper;

    @Autowired
    public PatientController(PatientDbService patientDbService, PatientMapper patientMapper) {
        this.patientDbService = patientDbService;
        this.patientMapper = patientMapper;
    }

    @GetMapping(value = "/patients")
    public List<PatientDto> getPatients() {
        return patientMapper.mapToPatientDtoList(patientDbService.getAllPatients());
    }

    @GetMapping(value = "/patients/{id}")
    public PatientDto getPatients(@PathVariable Long id) throws PatientNotFoundException {
        return patientMapper.mapToPatientDto(patientDbService.getPatientById(id).orElseThrow(PatientNotFoundException::new));
    }

    @DeleteMapping(value = "/patients/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientDbService.deletePatientById(id);

    }

    @PutMapping(value = "/patients")
    public PatientDto updatePatient(@RequestBody PatientDto patientDto) {
        return patientMapper.mapToPatientDto(patientDbService.savePatient(patientMapper.mapToPatient(patientDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/patients")
    public void createPatient(@RequestBody PatientDto patientDto) {
        patientDbService.savePatient(patientMapper.mapToPatient(patientDto));
    }
}
