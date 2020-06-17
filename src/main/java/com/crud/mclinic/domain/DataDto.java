package com.crud.mclinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDto {

    @JsonProperty("lastChecked")
    private String lastChecked;

    @JsonProperty("covid19Stats")
    private List<Covid19StatDto> covid19Stats;
}
