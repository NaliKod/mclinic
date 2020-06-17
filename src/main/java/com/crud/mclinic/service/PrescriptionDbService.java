package com.crud.mclinic.service;

import com.crud.mclinic.domain.Prescription;
import com.crud.mclinic.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionDbService {

    private PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionDbService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }

    public void deletePrescriptionById(Long id) {
        prescriptionRepository.deleteById(id);
    }

    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public List<Long> getListOfLongIdPrescriptions(final List<Prescription> prescriptions) {
        return prescriptions.stream()
                .map(prescription -> prescription.getId())
                .collect(Collectors.toList());
    }

    public List<Prescription> getListOfPrescriptions(final List<Long> prescriptionsId) {
        ArrayList<Prescription> prescriptionsList = new ArrayList<>();
        for (int i = 0; i < prescriptionsId.size(); i++) {
            Optional<Prescription> prescriptions = prescriptionRepository.findById(prescriptionsId.get(i));
            if (prescriptions.isPresent()) {
                prescriptionsList.add(prescriptions.get());
                return prescriptionsList;
            }
        }
        return prescriptionsList;
    }

    public List<Prescription> getPatientPrescriptions(Long patientId) {
        List<Prescription> prescriptionList = new ArrayList<>();
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        for (Prescription actualPrescription : prescriptions) {
            if (actualPrescription.getPatient().getId().equals(patientId))
                prescriptionList.add(actualPrescription);
            return prescriptionList;
        }
        return prescriptionList;
    }
}
