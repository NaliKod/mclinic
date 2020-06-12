package com.crud.mclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientHealthCardDto {

    private Long id;
    private String weight;
    private String height;
    private String description;
    private Long patientId;
    private Long visitId;
}
