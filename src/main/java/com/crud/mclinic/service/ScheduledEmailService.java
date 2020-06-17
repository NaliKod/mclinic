package com.crud.mclinic.service;

import com.crud.mclinic.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class ScheduledEmailService {

    private MailCreatorService mailCreatorService;
    private JavaMailSender javaMailSender;

    @Autowired
    public ScheduledEmailService(MailCreatorService mailCreatorService, JavaMailSender javaMailSender) {
        this.mailCreatorService = mailCreatorService;
        this.javaMailSender = javaMailSender;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledEmailService.class);


    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildPromotionVisitEmail(mail.getMessage()), true);
        };
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.buildPromotionVisitEmail(mail.getMessage()));
        return mailMessage;
    }

    public void send (final Mail mail) {
        LOGGER.info("Starting email preparation....");
        try {
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }
}
