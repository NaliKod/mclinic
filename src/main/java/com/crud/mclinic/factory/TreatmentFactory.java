package com.crud.mclinic.factory;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TreatmentFactory {

    public static final String HERBS = "HERBS";
    public static final String NUTRIENTS = "NUTRIENTS";
    public static final String DIET = "DIET";

    public final Treatment makeTreatment(final String treatmentClass) {
        switch (treatmentClass) {
            case HERBS:
                return new Herbs("HerbsNaturalTreatment", 50.0);
            case NUTRIENTS:
                return new Nutrients("NutrientsTreatment", 100.0);
            case DIET:
                return new Diet("DietTreatment", 40.0);
            default:
                return null;
        }
    }
}
