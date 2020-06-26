package com.crud.mclinic.service;

import com.crud.mclinic.domain.Opinion;
import com.crud.mclinic.repository.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpinionDbService {

    private OpinionRepository opinionRepository;

    @Autowired
    public OpinionDbService(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    public List<Opinion> getAllOpinions() {
        return opinionRepository.findAll();
    }

    public Optional<Opinion> getOpinionById(Long id) {
        return opinionRepository.findById(id);
    }

    public void deleteOpinionById(Long id) {
        opinionRepository.deleteById(id);
    }

    public Opinion saveOpinion(Opinion opinion) {
        return opinionRepository.save(opinion);
    }
}
