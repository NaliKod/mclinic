package com.crud.mclinic.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecializationDto {

    private Long id;
    private String name;
    private String description;
    private Long doctorId;
}
