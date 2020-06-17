package com.crud.mclinic.controller;

import com.crud.mclinic.domain.PatientHealthCardDto;
import com.crud.mclinic.mapper.PatientHealthCardMapper;
import com.crud.mclinic.service.PatientHealthCardDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<PatientHealthCardDto> getPatientHealthCards() {
        return patientHealthCardMapper.mapToPatientHealthCardsDtoList(patientHealthCardDbService.getAllPatientHealthCard());
    }

    @GetMapping(value = "/patientHealthCards/patients/{patientId}")
    public PatientHealthCardDto getPatientHealthCard(@PathVariable Long patientId) {
        return patientHealthCardMapper.mapToPatientHealthCardDto(patientHealthCardDbService.getPatientHealthCard(patientId));
    }

    @DeleteMapping(value = "/patientHealthCards")
    public void deletePatientHeathCard(@PathVariable Long id) {
        patientHealthCardDbService.deletePatientHealthCardById(id);

    }

    @PutMapping(value = "/patientHealthCards")
    public PatientHealthCardDto updatePatientHealtCard(@RequestBody PatientHealthCardDto patientHealthCardDto) {
        return patientHealthCardMapper.
                mapToPatientHealthCardDto(patientHealthCardDbService.
                        savePatientHealthCard(patientHealthCardMapper.
                                mapToPatientHealthCard(patientHealthCardDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/patientHealthCards")
    public void createPatienHealthCard(@RequestBody PatientHealthCardDto patientHealthCardDto) {
        patientHealthCardDbService.savePatientHealthCard(patientHealthCardMapper.mapToPatientHealthCard(patientHealthCardDto));
    }
}
