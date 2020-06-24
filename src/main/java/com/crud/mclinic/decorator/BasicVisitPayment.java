package com.crud.mclinic.decorator;

import java.math.BigDecimal;

public class BasicVisitPayment implements VisitPayment{

    @Override
    public BigDecimal getVisitCost() {
        return new BigDecimal(100);
    }

    @Override
    public String getTests() {
        return "Visit by any doctor";
    }
}
