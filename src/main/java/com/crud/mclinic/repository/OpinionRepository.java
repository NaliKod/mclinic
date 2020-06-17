package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Opinion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OpinionRepository extends CrudRepository<Opinion,Long> {

    @Override
    List<Opinion> findAll();

    @Override
    Opinion save(Opinion Opinion);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Opinion> findById(Long id);
}
