package com.crud.mclinic.decorator;

import java.math.BigDecimal;

public class X_RayOrderDecorator extends AbstractVisitPaymentDecorator {

    public X_RayOrderDecorator(VisitPayment visitPayment) {
        super(visitPayment);
    }

    @Override
    public BigDecimal getVisitCost() {
        return super.getVisitCost().add(new BigDecimal(60));
    }

    @Override
    public String getTests() {
        return super.getTests()+" medical examination ordered X-Ray";
    }
}
