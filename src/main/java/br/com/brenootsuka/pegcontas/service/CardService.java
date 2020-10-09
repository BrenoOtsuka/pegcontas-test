package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.model.*;
import br.com.brenootsuka.pegcontas.model.request.CardRequest;
import br.com.brenootsuka.pegcontas.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final BillService billService;
    private final PatientService patientService;
    private final ActivityService activityService;
    private final HealthInsuranceService healthInsuranceService;

    public CardService(
            CardRepository cardRepository,
            BillService billService,
            PatientService patientService,
            ActivityService activityService,
            HealthInsuranceService healthInsuranceService
    ) {
        this.cardRepository = cardRepository;
        this.billService = billService;
        this.patientService = patientService;
        this.activityService = activityService;
        this.healthInsuranceService = healthInsuranceService;
    }

    public Card save(CardRequest request) {

        Card card = null;
        Activity activity = activityService.findByActivityId(request.getActivityId());

        if (activity != null) {
            String patientName = request.getPatient();
            Patient patient = patientService.findByName(patientName);

            if (patient == null) {
                patient = new Patient(patientName);
            }

            String healthInsuranceName = request.getHealthInsurance();
            HealthInsurance healthInsurance = healthInsuranceService.findByName(healthInsuranceName);

            if (healthInsurance == null) {
                healthInsurance = new HealthInsurance(healthInsuranceName);
            }

            Bill bill = billService.save(request.getBill());

            card = request.getCard();
            card.setBill(bill);
            card.setPatient(patient);
            card.setActivity(activity);
            card.setHealthInsurance(healthInsurance);

            card = cardRepository.save(card);
        }
        return card;
    }

    public List<Card> findByActivityId(Long value) {

        Activity activity = activityService.findByActivityId(value);

        return cardRepository.findByActivity(activity);
    }

    public List<Card> findByVisitId(Long value) {
        return cardRepository.findByVisitId(value);
    }

    public List<Card> findByBillId(Long value) {

        Bill bill = billService.findByBillId(value);

        return cardRepository.findByBill(bill);
    }

    public List<Card> findByPatientName(String value) {

        Patient patient = patientService.findByName(value);

        return cardRepository.findByPatient(patient);
    }

    public List<Card> filterByPriority(List<Card> cards) {

        cards.sort(Comparator.comparing(Card::getDaysSinceCreated));
        Collections.reverse(cards);
        return cards;
    }

    public List<Card> filterByToReceive(List<Card> cards) {

        Predicate<Card> byDocumentsToReceive;
        byDocumentsToReceive = card -> card.getNumberOfNotReceivedDocuments() != 0;

        return cards.stream().filter(byDocumentsToReceive).collect(Collectors.toList());
    }

    public List<Card> filterByToSend(List<Card> cards) {

        Predicate<Card> byDocumentsToSend;
        byDocumentsToSend = card ->
                card.getNumberOfNotReceivedDocuments() == 0 &&
                card.getNumberOfDoneChecklistItem() == card.getNumberOfChecklistItem() &&
                card.getBill().getNumberOfOpenPendencies() == 0;

        return cards.stream().filter(byDocumentsToSend).collect(Collectors.toList());
    }
}
