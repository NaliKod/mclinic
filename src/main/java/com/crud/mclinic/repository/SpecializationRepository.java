package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Specialization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface SpecializationRepository extends CrudRepository<Specialization,Long> {

    @Override
    List<Specialization> findAll();

    @Override
    Specialization save(Specialization specialization);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Specialization> findById(Long id);
}
