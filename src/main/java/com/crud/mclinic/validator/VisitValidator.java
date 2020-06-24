package com.crud.mclinic.validator;

import com.crud.mclinic.domain.AppointmentDateTimeDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Component
public class VisitValidator {

    public boolean validateVisitTerm(LocalDateTime dateTime) {

        boolean validDateTime = false;
        AppointmentDateTimeDto appointmentDateTime = new AppointmentDateTimeDto();
        LocalTime time = dateTime.toLocalTime();
        LocalDate date = dateTime.toLocalDate();

        if (!date.isBefore(LocalDate.now())) {
            if ((date.getDayOfWeek().getValue() != 7) && (date.getDayOfWeek().getValue() != 6)) {
                if (((time.getHour() >= 8) && (time.getHour() <= 16))) {
                    for (LocalTime timeCheck : appointmentDateTime.appointmentTime()) {
                        if (time.equals(timeCheck)) {
                            validDateTime = true;
                            break;
                        }
                    }
                    LOGGER.info(" It is possible to book appointment at this time. Only in time slot every 30 minutes.");
                } else {
                    LOGGER.info("We are sorry but our clinic works from 8:00 am till 16:00 pm.");
                }
            } else {
                LOGGER.info("We are sorry but our clinic works except Saturday and Sunday");
                validDateTime = false;
            }
        }
        else{
            LOGGER.info("You can book an appointment only for a future date");
            validDateTime = false;
        }
        return validDateTime;
    }
}
