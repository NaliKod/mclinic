package com.crud.mclinic.repository;

import com.crud.mclinic.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentRepositoryTestSuite {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Test
    public void testSavePayment() {
        //Given
        Specialization specialization = Specialization.builder().id(null).name("laryngolog").build();
        Doctor doctor = Doctor.builder().id(null).name("Marina").surname("Kowalski").email("aaa@gmail.com").specialization(specialization).build();
        Patient patient = Patient.builder()
                .id(null).name("Anna").surname("Grant").sex('K').pesel("9999123")
                .email("anna.grant@gmail.com").address("US New York").build();
        Visit visit = Visit.builder().id(null).dateTimeVisit(LocalDateTime.of(LocalDate.of(2020, 7, 3), LocalTime.of(15, 0, 0))).isBooked(false).isClosed(false).doctor(doctor).patient(patient).build();
        Payment payment = Payment.builder().id(null).cost(new BigDecimal(150)).description("Payment for Visit").visit(visit).build();

        //When
        doctorRepository.save(doctor);
        Long doctorId = doctor.getId();
        patientRepository.save(patient);
        Long patientId = patient.getId();
        visitRepository.save(visit);
        Long visitId = visit.getId();
        paymentRepository.save(payment);
        Long paymentId = payment.getId();

        //Then
        Optional<Payment> actualPayment = paymentRepository.findById(paymentId);
        //Assert.assertTrue(actualPayment.isPresent());
        // assertEquals(new BigDecimal(150),actualPayment.get().getCost());
        //assertEquals("Payment for a visit",actualPayment.get().getDescription());

        //CleanUp
        try {
            patientRepository.deleteById(patientId);
            doctorRepository.deleteById(doctorId);
            visitRepository.deleteById(visitId);
            paymentRepository.deleteById(paymentId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
