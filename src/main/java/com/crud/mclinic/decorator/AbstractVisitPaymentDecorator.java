package com.crud.mclinic.decorator;

import java.math.BigDecimal;

public abstract class AbstractVisitPaymentDecorator implements VisitPayment {

    private VisitPayment visitPayment;

    public AbstractVisitPaymentDecorator(VisitPayment visitPayment) {
        this.visitPayment = visitPayment;
    }

    @Override
    public BigDecimal getVisitCost() {
        return visitPayment.getVisitCost();
    }

    @Override
    public String getTests() {
        return visitPayment.getTests();
    }

}
