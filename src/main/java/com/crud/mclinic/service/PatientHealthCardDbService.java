package com.crud.mclinic.service;

import com.crud.mclinic.domain.PatientHealthCard;
import com.crud.mclinic.repository.PatientHealthCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientHealthCardDbService {

    private PatientHealthCardRepository patientHealthCardRepository;

    @Autowired
    public PatientHealthCardDbService(PatientHealthCardRepository patientHealthCardRepository) {
        this.patientHealthCardRepository = patientHealthCardRepository;
    }

    public List<PatientHealthCard> getAllPatientHealthCard() {
        return patientHealthCardRepository.findAll();
    }

    public Optional<PatientHealthCard> getPatientHealthCardById(Long id) {
        return patientHealthCardRepository.findById(id);
    }

    public void deletePatienHealthCardById(Long id) {
        patientHealthCardRepository.deleteById(id);
    }

    public PatientHealthCard savePatientHealthCard(PatientHealthCard patientHealthCard) {
        return patientHealthCardRepository.save(patientHealthCard);
    }
}
