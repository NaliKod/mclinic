package com.crud.mclinic.repository;

import com.crud.mclinic.domain.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PaymentRepository extends CrudRepository<Payment,Long> {

    @Override
    List<Payment> findAll();

    @Override
    Payment save(Payment payment);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Payment> findById(Long id);
}
