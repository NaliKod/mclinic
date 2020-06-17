package com.crud.mclinic.service;

import com.crud.mclinic.config.AdminConfig;
import com.crud.mclinic.domain.Mail;
import com.crud.mclinic.domain.Visit;
import com.crud.mclinic.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class VisitDbService {

    private static final String SUBJECT = "Your visit has been booked";

    private VisitRepository visitRepository;
    private SimpleEmailService mailService;
    private AdminConfig adminConfig;

    @Autowired
    public VisitDbService(VisitRepository visitRepository, SimpleEmailService mailService, AdminConfig adminConfig) {
        this.visitRepository = visitRepository;
        this.mailService = mailService;
        this.adminConfig = adminConfig;
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

    public Visit bookVisit(Visit bookVisit) {

        List<Visit> allVisits = visitRepository.findAll();
        for (Visit actualVisit : allVisits) {
            if (actualVisit.getDoctor().getId().equals(bookVisit.getDoctor().getId()) && actualVisit.getDateVisit().isEqual(bookVisit.getDateVisit()) &&
                    actualVisit.getTimeVisit().equals(bookVisit.getTimeVisit())
                    && actualVisit.isBooked()) {
                System.out.println("Visit is already booked");
            } else {
                Visit newVisit = visitRepository.save(Visit.builder().dateVisit(bookVisit.getDateVisit()).timeVisit(bookVisit.getTimeVisit()).isBooked(true)
                        .doctor(bookVisit.getDoctor()).patient(bookVisit.getPatient()).build());

                ofNullable(newVisit).ifPresent(visit -> mailService.send(new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "You have booked visit by doctor: " + newVisit.getDoctor().getName() + " " + newVisit.getDoctor().getSurname() + ". Please arrive at least 15 Minutes early", "")));
                return newVisit;
            }
        }
        return null;
    }


    public void cancelVisit(Long id) {
        Optional<Visit> readVisit = visitRepository.findById(id);
        if (readVisit.isPresent()) {
            readVisit.get().setBooked(false);

            //send email your visit was cancelled

        }

    }
}
