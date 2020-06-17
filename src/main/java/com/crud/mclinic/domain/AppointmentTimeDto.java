package com.crud.mclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentTimeDto {

    List<LocalTime> times;

    public List<LocalTime> appoitmentTime() {

        int gapInMinutes = 30;  // Define your span-of-time.
        int loops = ((int) Duration.ofHours(8).toMinutes() / gapInMinutes);
        times = new ArrayList<>(loops);
        LocalTime time = LocalTime.of(8, 00);
        for (int i = 0; i <= loops; i++) {
            times.add(time);
            time = time.plusMinutes(gapInMinutes);
        }
        return times;
    }

    public List<LocalDate> appoitmentDate() {
        //GregorianCalendar calendar = appintmentDate;
        return new ArrayList<>();
    }
}
