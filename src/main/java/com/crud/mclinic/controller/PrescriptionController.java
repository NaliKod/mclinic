package com.crud.mclinic.controller;

import com.crud.mclinic.domain.PrescriptionDto;
import com.crud.mclinic.exceptions.PrescriptionNotFoundException;
import com.crud.mclinic.mapper.PrescriptionMapper;
import com.crud.mclinic.service.PrescriptionDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class PrescriptionController {

    private PrescriptionDbService prescriptionDbService;
    private PrescriptionMapper prescriptionMapper;

    @Autowired
    public PrescriptionController(PrescriptionDbService prescriptionDbService, PrescriptionMapper prescriptionMapper) {
        this.prescriptionDbService = prescriptionDbService;
        this.prescriptionMapper = prescriptionMapper;
    }

    @GetMapping(value = "/prescriptions/patients/{patientId}")
    public List<PrescriptionDto> getPrescriptions(@PathVariable Long patientId) {
        return prescriptionMapper.mapToPrescriptionDtoList(prescriptionDbService.getPatientPrescriptions(patientId));
    }

    @GetMapping(value = "/prescriptions/{id}")
    public PrescriptionDto getPrescription(@PathVariable Long id) throws PrescriptionNotFoundException {
        return prescriptionMapper.mapToPrescriptionDto(prescriptionDbService.getPrescriptionById(id).orElseThrow(PrescriptionNotFoundException::new));
    }

    @DeleteMapping(value = "/prescriptions/{id}")
    public void deletePrescription(@PathVariable Long id) {
        prescriptionDbService.deletePrescriptionById(id);
    }

    @PutMapping(value = "/prescriptions")
    public PrescriptionDto updatePrescription(@RequestBody PrescriptionDto prescriptionDto) {
        return prescriptionMapper.mapToPrescriptionDto(prescriptionDbService.
                savePrescription(prescriptionMapper.mapToPrescription(prescriptionDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/prescriptions")
    public void createPrescription(@RequestBody PrescriptionDto prescriptionDto) {
        prescriptionDbService.savePrescription(prescriptionMapper.mapToPrescription(prescriptionDto));
    }
}
