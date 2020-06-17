package com.crud.mclinic.domain;

import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private Long specializationId;
   }
