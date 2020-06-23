package com.crud.mclinic.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitDto {

    private Long id;
    private LocalDateTime dateTimeVisit;
    private boolean isBooked;
    private boolean isClosed;
    private Long doctorId;
    private Long patientId;
}
