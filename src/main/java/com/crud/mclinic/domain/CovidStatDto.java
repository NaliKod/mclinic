package com.crud.mclinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidStatDto {

    @JsonProperty("province")
    private String province;

    @JsonProperty("country")
    private String country;

    @JsonProperty("lastUpdate")
    private String lastUpdate;

    @JsonProperty("confirmed")
    private Integer confirmed;

    @JsonProperty("deaths")
    private Integer deaths;

    @JsonProperty("recovered")
    private Integer recovered;
}
