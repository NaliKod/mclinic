package com.crud.mclinic.service;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorDbService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorDbService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getAllDoctorsWitSpecialization(Long specializationId) {
        ArrayList<Doctor> withSpecialization = new ArrayList<>();
        for (Doctor searchDoctor : getAllDoctors()) {
            if (searchDoctor.getSpecializations().equals(specializationId)) {
                withSpecialization.add(searchDoctor);
            }
        }
        return withSpecialization;
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public void deleteDoctorById(Long id) {
        doctorRepository.deleteById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
