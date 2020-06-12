package com.crud.mclinic.service;

import com.crud.mclinic.domain.Visit;
import com.crud.mclinic.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitDbService {

    private VisitRepository visitRepository;

    @Autowired
    public VisitDbService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Optional<Visit> getVisitById(final Long id) {
        return visitRepository.findById(id);
    }

    public void deleteVisitById(final Long id) {
        visitRepository.deleteById(id);
    }

    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public List<Long> getListOfLongIdVisits(final List<Visit> visits) {
        return visits.stream()
                .map(visit -> visit.getId())
                .collect(Collectors.toList());
    }

    public List<Visit> getListOfVisits(final List<Long> visitsId) {
        ArrayList<Visit> visitsList = new ArrayList<>();
        for (int i = 0; i < visitsId.size(); i++) {
            Optional<Visit> visits = visitRepository.findById(visitsId.get(i));
            if (visits.isPresent()) {
                visitsList.add(visits.get());
                return visitsList;
            }
        }
        return visitsList;
    }
}
