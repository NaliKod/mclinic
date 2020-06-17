package com.crud.mclinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Covid19StatDto {

    @JsonProperty("city")
    private String city;

    @JsonProperty("province")
    private String province;

    @JsonProperty("country")
    private String country;

    @JsonProperty("keyId")
    private String keyId;

    @JsonProperty("lastUpdate")
    private String lastUpdate;

    @JsonProperty("confirmed")
    private Integer confirmed;

    @JsonProperty("deaths")
    private Integer deaths;

    @JsonProperty("recovered")
    private Integer recovered;
}
