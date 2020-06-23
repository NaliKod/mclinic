package com.crud.mclinic.service;

import com.crud.mclinic.config.AdminConfig;
import com.crud.mclinic.domain.Mail;
import com.crud.mclinic.domain.Visit;
import com.crud.mclinic.repository.VisitRepository;
import com.crud.mclinic.validator.VisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class VisitDbService {

    private static final String SUBJECT = "Your visit has been booked";
    private static final String SUBJECT_CANCELLED_ = "Your visit has been cancelled";

    private VisitRepository visitRepository;
    private ScheduledPatientEmailService mailService;
    private AdminConfig adminConfig;
    private VisitValidator visitValidator;

    @Autowired
    public VisitDbService(VisitRepository visitRepository, ScheduledPatientEmailService mailService, AdminConfig adminConfig, VisitValidator visitValidator) {
        this.visitRepository = visitRepository;
        this.mailService = mailService;
        this.adminConfig = adminConfig;
        this.visitValidator = visitValidator;
    }

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Optional<Visit> getVisitById(final Long id) {
        return visitRepository.findById(id);
    }

    public void deleteVisitById(final Long id) {
        visitRepository.deleteById(id);
    }

    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public List<Long> getListOfLongIdVisits(final List<Visit> visits) {
        return visits.stream()
                .map(visit -> visit.getId())
                .collect(Collectors.toList());
    }

    public List<Visit> getListOfVisits(final List<Long> visitsId) {
        ArrayList<Visit> visitsList = new ArrayList<>();
        for (int i = 0; i < visitsId.size(); i++) {
            Optional<Visit> visits = visitRepository.findById(visitsId.get(i));
            if (visits.isPresent()) {
                visitsList.add(visits.get());
                return visitsList;
            }
        }
        return visitsList;
    }

    public void bookVisit(final Visit bookVisit) {

        boolean free_visit = true;

        if (visitValidator.validateVisitTerm(bookVisit.getDateTimeVisit())) {
            List<Visit> allVisits = visitRepository.findAll();

            for (Visit actualVisit : allVisits) {
                if (actualVisit.getDoctor().getId().equals(bookVisit.getDoctor().getId()) && actualVisit.getDateTimeVisit().isEqual(bookVisit.getDateTimeVisit()) && actualVisit.isBooked()) {
                    LOGGER.info("We are sorry but this appointment is not available. Please try another one.");
                    free_visit = false;
                    break;
                }
                if (actualVisit.getPatient().equals(bookVisit.getPatient()) && actualVisit.getDateTimeVisit().isEqual(bookVisit.getDateTimeVisit()) && (actualVisit.isBooked())) {
                    LOGGER.info("You have already booked visit at this time");
                    free_visit = false;
                    break;
                }
            }
            if (free_visit == true || allVisits.isEmpty()) {
                Visit newVisit = visitRepository.save(Visit.builder().dateTimeVisit(bookVisit.getDateTimeVisit()).isBooked(true).isClosed(false)
                        .doctor(bookVisit.getDoctor()).patient(bookVisit.getPatient()).build());

                ofNullable(newVisit).ifPresent(visit -> mailService.send(new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "You have booked visit by doctor: " + visit.getDoctor().getName() + " " + visit.getDoctor().getSurname() + ". Please arrive at least 15 Minutes early", "")));
            }
        }
    }

    public void cancelVisit(Long id) {
        Optional<Visit> cancelVisit = visitRepository.findById(id);
        visitRepository.deleteById(id);
        ofNullable(cancelVisit).ifPresent(visit -> mailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Your visit has been cancelled by " + visit.get().getPatient().getName() + " " + visit.get().getPatient().getSurname()
                        + ". At: " + visit.get().getDateTimeVisit(), "")));
    }
}
