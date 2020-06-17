package com.crud.mclinic.scheduler;

import com.crud.mclinic.config.AdminConfig;
import com.crud.mclinic.domain.Mail;
import com.crud.mclinic.domain.Visit;
import com.crud.mclinic.service.ScheduledEmailService;
import com.crud.mclinic.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private ScheduledEmailService scheduledEmailService;

    @Autowired
    private AdminConfig adminConfig;



    private static final String SUBJECT = "We have new promotion for our treatment";
    private static final String SUBJECT_Remainder ="Remainder about upcoming visit";
    private static final String SUBJECT_ActualSituationWitCovid19 ="We would like to inform you about actual situation in Poland";



   /* @Scheduled(cron = "0 0 9-17 * * MON-FRI")
    //@Scheduled(fixedDelay = 10000)
    public void scheduledEmail(){
        scheduledEmailService.send( new Mail(adminConfig.getAdminMail(),SUBJECT,"Please contact you doctor",""));
    }*/

   // @Scheduled(cron = "0 0 9-17 * * MON-FRI")
    @Scheduled(fixedDelay = 10000)
    public void scheduledVisitEmail(){
        scheduledEmailService.send( new Mail(adminConfig.getAdminMail(),SUBJECT_ActualSituationWitCovid19,
                " Amount of ",""));
    }
}
