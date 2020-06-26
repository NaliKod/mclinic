package com.crud.mclinic.controller;

import com.crud.mclinic.domain.OpinionDto;
import com.crud.mclinic.exceptions.OpinionNotFoundException;
import com.crud.mclinic.mapper.OpinionMapper;
import com.crud.mclinic.service.OpinionDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1")
public class OpinionController {

    @Autowired
    private OpinionDbService opinionDbService;

    @Autowired
    private OpinionMapper opinionMapper;

    @GetMapping(value = "/opinions")
    public List<OpinionDto> getOpinion() {
        return opinionMapper.mapToOpinionDtoList(opinionDbService.getAllOpinions());
    }

    @GetMapping(value = "/opinions/{id}")
    public OpinionDto getOpinion(@PathVariable Long id) throws OpinionNotFoundException {
        return opinionMapper.mapToOpinionDto(opinionDbService.getOpinionById(id).orElseThrow(OpinionNotFoundException::new));
    }

    @DeleteMapping(value = "/opinions/{id}")
    public void deleteOpinion(@PathVariable Long id) {
        opinionDbService.deleteOpinionById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/opinions")
    public void createOpinion(@RequestBody OpinionDto opinionDto) {
        opinionDbService.saveOpinion(opinionMapper.mapToOpinion(opinionDto));
    }

}
