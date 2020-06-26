package com.crud.mclinic.decorator;

import java.math.BigDecimal;

public class HolterOrderDecorator extends AbstractVisitPaymentDecorator{

    public HolterOrderDecorator(VisitPayment visitPayment) {
        super(visitPayment);
    }

    @Override
    public BigDecimal getVisitCost() {
        return super.getVisitCost().add(new BigDecimal(150));
    }

    @Override
    public String getTests() {
        return super.getTests()+ "medical examination ordered Holter";
    }
}
