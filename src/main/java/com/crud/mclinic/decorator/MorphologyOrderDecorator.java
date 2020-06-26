package com.crud.mclinic.decorator;

import java.math.BigDecimal;

public class MorphologyOrderDecorator extends AbstractVisitPaymentDecorator{

    public MorphologyOrderDecorator(VisitPayment visitPayment) {
        super(visitPayment);
    }

    @Override
    public BigDecimal getVisitCost() {
        return super.getVisitCost().add(new BigDecimal(35));
    }

    @Override
    public String getTests() {
        return super.getTests()+"medical examination ordered Blood test";
    }
}
