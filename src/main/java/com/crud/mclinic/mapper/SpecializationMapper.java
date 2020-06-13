package com.crud.mclinic.mapper;

import com.crud.mclinic.domain.Specialization;
import com.crud.mclinic.domain.SpecializationDto;
import com.crud.mclinic.service.DoctorDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecializationMapper {

  private DoctorDbService doctorDbService;

    @Autowired
    public SpecializationMapper(DoctorDbService doctorDbService) {
        this.doctorDbService = doctorDbService;
    }

    public Specialization mapToSpecialization(final SpecializationDto specializationDto) {
        return Specialization.builder()
                .id(specializationDto.getId())
                .name(specializationDto.getName())
                .description(specializationDto.getDescription())
                .build();
    }

    public SpecializationDto mapToSpecializationDto(final Specialization specialization) {
        return SpecializationDto.builder()
                .id(specialization.getId())
                .name(specialization.getName())
                .description(specialization.getDescription())
                .build();
    }

    public List<Specialization> mapToSpecializationList(final List<SpecializationDto> specializationList) {
        return specializationList.stream()
                .map(s -> Specialization.builder().id(s.getId()).name(s.getName()).description(s.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public List<SpecializationDto> mapToSpecializationDtoList(final List<Specialization> specializationList) {
        return specializationList.stream()
                .map(s -> SpecializationDto.builder().id(s.getId()).name(s.getName()).description(s.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
