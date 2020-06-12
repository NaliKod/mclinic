package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Room;
import com.crud.mclinic.domain.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface VisitRepository extends CrudRepository<Visit,Long> {

    @Override
    List<Visit> findAll();

    @Override
    Visit save(Visit visit);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Visit> findById(Long id);
}
