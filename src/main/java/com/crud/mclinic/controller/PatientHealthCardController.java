package com.crud.mclinic.controller;

import com.crud.mclinic.domain.PatientHealthCardDto;
import com.crud.mclinic.exceptions.PatientNotFoundException;
import com.crud.mclinic.mapper.PatientHealthCardMapper;
import com.crud.mclinic.service.PatientHealthCardDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class PatientHealthCardController {

    private PatientHealthCardDbService patientHealthCardDbService;
    private PatientHealthCardMapper patientHealthCardMapper;

    @Autowired
    public PatientHealthCardController(PatientHealthCardDbService patientHealthCardDbService, PatientHealthCardMapper patientHealthCardMapper) {
        this.patientHealthCardDbService = patientHealthCardDbService;
        this.patientHealthCardMapper = patientHealthCardMapper;
    }

    @GetMapping(value = "/patientHealthCards")
    public List<PatientHealthCardDto> getPatientHealthCards(){
        return new ArrayList<>();
    }

    @GetMapping(value = "/patientHealthCards/{id}")
    public PatientHealthCardDto getPatientHealthCard(@PathVariable Long id) throws PatientNotFoundException {
        return patientHealthCardMapper.mapToPatientHealthCardDto(patientHealthCardDbService.getPatientHealthCardById(id).orElseThrow(PatientNotFoundException::new));
    }

    @DeleteMapping(value = "/patientHealthCards/{id}")
    public void deletePatientHeathCard(@PathVariable Long id){
        patientHealthCardDbService.deletePatienHealthCardById(id);

    }

    @PutMapping(value = "/patientHealthCards")
    public PatientHealthCardDto updatePatientHealtCard(@RequestBody PatientHealthCardDto patientHealthCardDto){
        return patientHealthCardMapper.
                mapToPatientHealthCardDto(patientHealthCardDbService.
                        savePatientHealthCard(patientHealthCardMapper.
                                mapToPatientHealthCard(patientHealthCardDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value="/patientHealthCards")
    public void createPatienHealthCard(@RequestBody PatientHealthCardDto patientHealthCardDto){
        patientHealthCardDbService.savePatientHealthCard(patientHealthCardMapper.mapToPatientHealthCard(patientHealthCardDto));
    }
}
