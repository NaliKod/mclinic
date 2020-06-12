package com.crud.mclinic.domain;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {

    private Long id;
    private String name;
    private String surname;
    private List<Long>specializationId;
    private List<Long> visitId;
    private List<Long> prescriptionId;

}
