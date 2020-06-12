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
public class RoomDto {

    private Long id;
    private Integer number;
    private Integer floor;
    private Long patientId;
    private Long doctorId;
    private List<Long> visitId;
}
