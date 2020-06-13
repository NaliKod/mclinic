package com.crud.mclinic.controller;

import com.crud.mclinic.domain.Visit;
import com.crud.mclinic.domain.VisitDto;
import com.crud.mclinic.exceptions.VisitNotFoundException;
import com.crud.mclinic.mapper.VisitMapper;
import com.crud.mclinic.service.VisitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class VisitController {

    private VisitDbService visitDbService;
    private VisitMapper visitMapper;

    @Autowired
    public VisitController(VisitDbService visitDbService, VisitMapper visitMapper) {
        this.visitDbService = visitDbService;
        this.visitMapper = visitMapper;
    }

    @GetMapping("/visits")
    public List<VisitDto> getVisits() {
        return visitMapper.mapToVisitDtoList(visitDbService.getAllVisits());
    }

    @GetMapping(value = "/visits/{id}")
    public VisitDto getVist(@PathVariable Long id) throws VisitNotFoundException {
        return visitMapper.mapToVisitDto(visitDbService.getVisitById(id).orElseThrow(VisitNotFoundException::new));
    }

    @DeleteMapping(value = "/visits/{id}")
    public void deleteVisist(@PathVariable Long id) {
        visitDbService.deleteVisitById(id);
    }

    @PutMapping(value = "/visits")
    public VisitDto updateVisit(@RequestBody VisitDto visitDto) {
        return visitMapper.mapToVisitDto(visitDbService.saveVisit(visitMapper.mapToVisit(visitDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/visits")
    public void createVisit(@RequestBody Visit visit) {
    }
}
