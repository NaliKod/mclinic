package com.crud.mclinic.controller;

import com.crud.mclinic.domain.Visit;
import com.crud.mclinic.domain.VisitDto;
import com.crud.mclinic.exceptions.VisitNotFoundException;
import com.crud.mclinic.factory.Herbs;
import com.crud.mclinic.factory.TreatmentFactory;
import com.crud.mclinic.mapper.VisitMapper;
import com.crud.mclinic.repository.VisitRepository;
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
    private VisitRepository visitRepository;

    @Autowired
    public VisitController(VisitDbService visitDbService, VisitMapper visitMapper, VisitRepository visitRepository) {
        this.visitDbService = visitDbService;
        this.visitMapper = visitMapper;
        this.visitRepository = visitRepository;
    }

    @GetMapping("/visits")
    public List<VisitDto> getVisits() {
        return visitMapper.mapToVisitDtoList(visitDbService.getAllVisits());
    }

    @GetMapping(value = "/visits/{id}")
    public VisitDto getVisit(@PathVariable Long id) throws VisitNotFoundException {
        return visitMapper.mapToVisitDto(visitDbService.getVisitById(id).orElseThrow(VisitNotFoundException::new));
    }

    @DeleteMapping(value = "/visits/{id}")
    public void cancelVisist(@PathVariable Long id) {
        visitDbService.cancelVisit(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/visits")
    public void bookVisit(@RequestBody VisitDto visitDto) {
        visitDbService.bookVisit(visitMapper.mapToVisit(visitDto));
    }
}
