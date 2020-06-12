package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.PatientHealthCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PatientHealthCardRepository extends CrudRepository<PatientHealthCard,Long> {

    @Override
    List<PatientHealthCard> findAll();

    @Override
    PatientHealthCard save(PatientHealthCard patientHealthCard);

    @Override
    void deleteById(Long id);

    @Override
    Optional<PatientHealthCard> findById(Long id);
}
