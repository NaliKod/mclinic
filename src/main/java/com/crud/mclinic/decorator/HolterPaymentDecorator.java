package com.crud.mclinic.decorator;

import java.math.BigDecimal;

public class HolterPaymentDecorator implements VisitPayment{

    @Override
    public BigDecimal getVisitCost() {
        return new BigDecimal(150);
    }

    @Override
    public String getTests() {
        return "Holter test";
    }
}
