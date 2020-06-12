package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Prescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription,Long> {

    @Override
    List<Prescription> findAll();

    @Override
    Prescription save(Prescription prescription);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Prescription> findById(Long id);
}
