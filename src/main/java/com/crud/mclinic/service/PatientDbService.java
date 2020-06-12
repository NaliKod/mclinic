package com.crud.mclinic.service;

import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientDbService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientDbService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
}
