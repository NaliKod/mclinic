package com.crud.mclinic.service;


import com.crud.mclinic.decorator.BasicVisitPayment;
import com.crud.mclinic.decorator.HolterOrderDecorator;
import com.crud.mclinic.decorator.MorphologyOrderDecorator;
import com.crud.mclinic.decorator.VisitPayment;

import com.crud.mclinic.domain.Payment;
import com.crud.mclinic.domain.Visit;
import com.crud.mclinic.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class PaymentDbService {

    private PaymentRepository paymentRepository;
    private VisitDbService visitDbService;

    @Autowired
    public PaymentDbService(PaymentRepository paymentRepository, VisitDbService visitDbService) {
        this.paymentRepository = paymentRepository;
        this.visitDbService = visitDbService;
    }

    public Optional<Payment> getPaymentById(Long id){
        return paymentRepository.findById(id);
    }


    public Payment createBasicVisitPayment(double currencyRate, Long visitId) {
        Payment actualPayment= new Payment();
        VisitPayment payment = new BasicVisitPayment();
        BigDecimal rate = new BigDecimal(currencyRate);
        Optional<Visit>actualVisit = visitDbService.getVisitById(visitId);
        if(actualVisit.isPresent() && !actualVisit.get().isClosed()){
            Visit closeVisit = actualVisit.get();
            closeVisit.setClosed(true);
            visitDbService.saveVisit(closeVisit);
            actualPayment.setCost(payment.getVisitCost().divide(rate,1, RoundingMode.HALF_UP));
            actualPayment.setDescription(payment.getTests());
            actualPayment.setVisit(visitDbService.getVisitById(visitId).get());
            paymentRepository.save(actualPayment);
        }
        return actualPayment;
    }

    public BigDecimal withMorphology(){
        VisitPayment payment = new BasicVisitPayment();
        payment= new MorphologyOrderDecorator(payment);
        return payment.getVisitCost();
    }

    public BigDecimal withHolter(){
        VisitPayment payment = new BasicVisitPayment();
        payment = new HolterOrderDecorator(payment);
        return payment.getVisitCost();
    }
}
