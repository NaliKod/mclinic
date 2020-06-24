package com.crud.mclinic.decorator;

import java.math.BigDecimal;

public class MorphologyPaymentDecorator implements VisitPayment{

    @Override
    public BigDecimal getVisitCost() {
        return new BigDecimal(35);
    }

    @Override
    public String getTests() {
        return "Basic blood test";
    }
}
