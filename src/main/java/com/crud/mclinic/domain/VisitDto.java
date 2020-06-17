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

    /*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime visitDateTime;*/
    private LocalDate dateVisit;
    private LocalTime timeVisit;
    private boolean isBooked;
    private boolean isClosed;
    private Long doctorId;
    private Long patientId;
}
