package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Doctor;
import com.crud.mclinic.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PatientRepository extends CrudRepository<Patient,Long> {

    @Override
    List<Patient> findAll();

    @Override
    Patient save(Patient patient);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Patient> findById(Long id);
}
