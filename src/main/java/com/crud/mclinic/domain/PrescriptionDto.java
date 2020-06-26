package com.crud.mclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionDto {

    private Long id;
    private String drug;
    private boolean chronicDisease;
    private String dosing;
    private Integer duration;
    private LocalDate date;
    private Long doctorId;
    private Long patientId;


    public Long getId() {
        return id;
    }

    public String getDrug() {
        return drug;
    }

    public boolean isChronicDisease() {
        return chronicDisease;
    }

    public String getDosing() {
        return dosing;
    }

    public Integer getDuration() {
        return duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }
}
