package com.crud.mclinic.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name.patient}")
    private String adminNamePatient;

    @Value("${admin.name.doctor}")
    private String adminNameDoctor;

}
