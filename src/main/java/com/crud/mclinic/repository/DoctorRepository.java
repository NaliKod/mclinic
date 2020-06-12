package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Long> {

    @Override
    List<Doctor> findAll();

    @Override
    Doctor save(Doctor doctor);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Doctor> findById(Long id);

}
