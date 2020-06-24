package com.crud.mclinic.decorator;

import java.math.BigDecimal;

public class X_RayDecorator implements VisitPayment {

    @Override
    public BigDecimal getVisitCost() {
        return new BigDecimal(60);
    }

    @Override
    public String getTests() {
        return "X-ray test";
    }
}
