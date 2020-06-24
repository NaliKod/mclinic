package com.crud.mclinic.decorator;

import java.math.BigDecimal;

public interface VisitPayment {

    BigDecimal getVisitCost();
    String getTests();
}
