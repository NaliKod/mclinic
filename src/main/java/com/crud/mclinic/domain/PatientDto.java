package com.crud.mclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDto {

    private Long id;
    private String name;
    private String surname;
    private char sex;
    private String pesel;
    private String email;
    private String address;
    private List<Long> visitId;
    private List<Long> prescriptionId;
}
