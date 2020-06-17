package com.crud.mclinic.controller;

import com.crud.mclinic.domain.OpinionDto;
import com.crud.mclinic.exceptions.OpinionNotFoundException;
import com.crud.mclinic.factory.Treatment;
import com.crud.mclinic.factory.TreatmentFactory;
import com.crud.mclinic.mapper.OpinionMapper;
import com.crud.mclinic.service.OpinionDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1")
public class OpinionController {

    @Autowired
    private OpinionDbService opinionDbService;

    @Autowired
    private OpinionMapper opinionMapper;


    @GetMapping(value = "/opinions/{id}")
    public OpinionDto getRoom(@PathVariable Long id) throws OpinionNotFoundException {
        return opinionMapper.mapToOpinionDto(opinionDbService.getOpinionById(id).orElseThrow(OpinionNotFoundException::new));
    }

    @DeleteMapping(value = "/opinions/{id}")
    public void deleteOpinion(@PathVariable Long id) {
        opinionDbService.deleteOpinionById(id);
    }

    @PutMapping(value = "/opinions")
    public OpinionDto updateRoom(@RequestBody OpinionDto opinionDto) {
        return opinionMapper.mapToOpinionDto(opinionDbService.saveOpinion(opinionMapper.mapToOpinion(opinionDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/opinions")
    public void createOpinion(@RequestBody OpinionDto opinionDto) {
        opinionDbService.saveOpinion(opinionMapper.mapToOpinion(opinionDto));
    }

}
