package com.crud.mclinic.service;

import com.crud.mclinic.domain.Specialization;
import com.crud.mclinic.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecializationDbService {

    private SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationDbService(SpecializationRepository specializationRepositoryRepository) {
        this.specializationRepository = specializationRepositoryRepository;
    }

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    public Optional<Specialization> getSpecializationById(Long id) {
        return specializationRepository.findById(id);
    }

    public void deleteSpecializationById(Long id) {
        specializationRepository.deleteById(id);
    }

    public Specialization saveSpecialization(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    public List<Long> getListOfLongIdSpecializations(final List<Specialization> specializations) {
        return specializations.stream()
                .map(specialization -> specialization.getId())
                .collect(Collectors.toList());
    }

    public List<Specialization> getListOfSpecializations(final List<Long> specializationsId) {
        ArrayList<Specialization> specializationsList = new ArrayList<>();
        for (int i = 0; i < specializationsId.size(); i++) {
            Optional<Specialization> specializations = specializationRepository.findById(specializationsId.get(i));
            if (specializations.isPresent()) {
                specializationsList.add(specializations.get());
                return specializationsList;
            }
        }
        return specializationsList;
    }
}
