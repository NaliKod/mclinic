package com.crud.mclinic.service;

import com.crud.mclinic.config.AdminConfig;
import com.crud.mclinic.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildBookVisitEmail(String message) {

        List<String> possibility = new ArrayList<>();
        possibility.add("Our goal is to keep you healthy");
        possibility.add("We provide additional treatments like : NaturalTherapy, NutrientsTherapy, DietTherapy");
        possibility.add("You can pay in any currency");
        String goodbye_message = "Have a nice day";
        String regards_message = "Best regards";
        String team_message = "MClinic Team";

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("goodbye_message", goodbye_message);
        context.setVariable("regards_message", regards_message);
        context.setVariable("team_message", team_message);
        context.setVariable("admin_name_patient", adminConfig.getAdminNamePatient());
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_possibility", possibility);
        return templateEngine.process("mail/created-patient-mail", context);
    }

    public String buildPromotionVisitEmail(String message) {
        Context context = new Context();
        String goodbye_message = "Have a nice day";
        String regards_message = "Best regards";
        String team_message = "MClinic team";
        context.setVariable("message", message);
        context.setVariable("goodbye_message", goodbye_message);
        context.setVariable("regards_message", regards_message);
        context.setVariable("team_message", team_message);
        //context.setVariable("tasks_url", "https://nalikod.github.io");
        //context.setVariable("button", "Visit website");
        context.setVariable("admin_name_doctor", adminConfig.getAdminNamePatient());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        return templateEngine.process("mail/promotion-patient-visit-mail", context);
    }
}
