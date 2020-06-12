package com.crud.mclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitDto {

    private Long id;
    private LocalDate visitDate;
    private LocalTime time;
    private boolean isPending;
    boolean isConfirmed;
    private Long doctorId;
    private Long patientId;
    private Long roomId;
}
