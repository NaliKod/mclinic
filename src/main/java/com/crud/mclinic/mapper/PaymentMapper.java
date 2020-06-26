package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Payment;
import com.crud.mclinic.domain.PaymentDto;
import com.crud.mclinic.service.PaymentDbService;
import com.crud.mclinic.service.VisitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {


    private VisitDbService visitDbService;

    @Autowired
    public PaymentMapper(VisitDbService visitDbService) {
        this.visitDbService = visitDbService;
    }


    public PaymentDto mapToPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .cost(payment.getCost())
                .description(payment.getDescription())
                .visitId(payment.getVisit().getId())
                .build();
    }

    public Payment mapToPayment(PaymentDto paymentDto) {
        return Payment.builder()
                .id(paymentDto.getId())
                .cost(paymentDto.getCost())
                .description(paymentDto.getDescription())
                .visit(visitDbService.getVisitById(paymentDto.getId()).get())
                .build();
    }
}
