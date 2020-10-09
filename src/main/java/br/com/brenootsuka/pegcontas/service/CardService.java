package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.model.Bill;
import br.com.brenootsuka.pegcontas.model.Card;
import br.com.brenootsuka.pegcontas.model.HealthInsurance;
import br.com.brenootsuka.pegcontas.model.Patient;
import br.com.brenootsuka.pegcontas.model.request.CardRequest;
import br.com.brenootsuka.pegcontas.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CardService {

    private CardRepository cardRepository;
    private BillService billService;
    private PatientService patientService;
    private ActivityService activityService;
    private HealthInsuranceService healthInsuranceService;

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

        Patient patient = patientService.findByName(request.getPatientName());

        if (patient == null) {
            patient = new Patient(request.getPatientName());
        }

        HealthInsurance healthInsurance = healthInsuranceService.findByName(request.getHealthInsuranceName());

        if (healthInsurance == null) {
            healthInsurance = new HealthInsurance(request.getHealthInsuranceName());
        }

        Bill bill = new Bill(
                request.getBillType(),
                request.getNumberOfPendencies(),
                request.getNumberOfOpenPendencies(),
                request.getTotalAmount()
        );
        bill = billService.save(bill);

        Card card = new Card(
                request.getSlaStatus(),
                request.getVisitId(),
                request.getDaysSinceCreated(),
                request.getNumberOfChecklistItem(),
                request.getNumberOfDoneChecklistItem(),
                request.getNumberOfDocuments(),
                request.getNumberOfNotReceivedDocuments()
        );

        card.setBill(bill);
        card.setPatient(patient);
        card.setHealthInsurance(healthInsurance);

        return cardRepository.save(card);
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
