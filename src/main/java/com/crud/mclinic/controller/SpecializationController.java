package com.crud.mclinic.controller;

import com.crud.mclinic.domain.SpecializationDto;
import com.crud.mclinic.exceptions.SpecializationNotFoundException;
import com.crud.mclinic.mapper.SpecializationMapper;
import com.crud.mclinic.service.SpecializationDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(name="/v1")
public class SpecializationController {

    private SpecializationDbService specializationDbService;
    private SpecializationMapper specializationMapper;

    @Autowired
    public SpecializationController(SpecializationDbService specializationDbService, SpecializationMapper specializationMapper) {
        this.specializationDbService = specializationDbService;
        this.specializationMapper = specializationMapper;
    }

    @GetMapping(value = "/specializations")
    public List<SpecializationDto> getSpecializations() {
        return specializationMapper.mapToSpecializationDtoList(specializationDbService.getAllSpecializations());
    }

    @GetMapping(value = "/specializations/{id}")
    public SpecializationDto getSpecialization(@PathVariable Long id) throws SpecializationNotFoundException {
        return specializationMapper.mapToSpecializationDto(specializationDbService.getSpecializationById(id).orElseThrow(SpecializationNotFoundException::new));
    }

    @DeleteMapping(value = "/specializations/{id}")
    public void deleteSpecialization(@PathVariable Long id) {
        specializationDbService.deleteSpecializationById(id);
    }

    @PutMapping(value = "/specializations")
    public SpecializationDto updateSpecialization(@RequestBody SpecializationDto specializationDto) {
        return specializationMapper.mapToSpecializationDto(specializationDbService.saveSpecialization
                (specializationMapper.mapToSpecialization(specializationDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/specializations")
    public void createSpecialization(@RequestBody SpecializationDto specializationDto) {
        specializationDbService.saveSpecialization(specializationMapper.mapToSpecialization(specializationDto));
    }
}
