package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Patient;
import com.crud.mclinic.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {

    @Override
    List<Room> findAll();

    @Override
    Room save(Room room);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Room> findById(Long id);
}
