package br.com.brenootsuka.pegcontas.service;

import br.com.brenootsuka.pegcontas.commons.enums.BillType;
import br.com.brenootsuka.pegcontas.commons.enums.SlaStatus;
import br.com.brenootsuka.pegcontas.model.*;
import br.com.brenootsuka.pegcontas.model.request.CardRequest;
import br.com.brenootsuka.pegcontas.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CardServiceTest {

    @Autowired
    private CardService service;

    @MockBean
    private CardRepository repository;

    @MockBean
    private ActivityService activityService;

    @MockBean
    private BillService billService;

    @MockBean
    private HealthInsuranceService healthInsuranceService;

    @MockBean
    private PatientService patientService;

    private CardRequest setupCardRequest() {
        return new CardRequest(
                (long) 1,
                20,
                SlaStatus.DELAYED,
                (long) 266684,
                5,
                1,
                0,
                0,
                BillType.HOSPITALAR,
                (float) 8925.55,
                0,
                0,
                "Maxima Seguro",
                "Fernando Leite Serrano"
        );
    }

    private CardRequest setupCardRequest2() {
        return new CardRequest(
                (long) 1,
                1,
                SlaStatus.OK,
                (long) 274084,
                5,
                0,
                0,
                0,
                BillType.HOSPITALAR,
                (float) 30814.31,
                0,
                0,
                "Unicorp Plan",
                "Veronice Martins Vitoria"
        );
    }

    private Card setupCard(CardRequest request) {

        Card card = request.getCard();
        card.setHealthInsurance(new HealthInsurance(request.getHealthInsurance()));
        card.setPatient(new Patient(request.getPatient()));
        card.setBill(request.getBill());

        return card;
    }

    private Activity setupActivity() {
        Activity activity = new Activity(
                "OPME",
                "Finalizar conta",
                5
        );
        activity.setActivityId((long) 1);

        return activity;
    }

    private Bill setupBill() {
        return new Bill(
                BillType.HOSPITALAR,
                0,
                0,
                (float) 8925.55
        );
    }

    private Patient setupPatient() {
        return new Patient("Fernando Leite Serrano");
    }

    private List<Card> setupUnsortedCards() {

        Card card1 = setupCard(setupCardRequest());
        Card card2 = setupCard(setupCardRequest2());

        return Arrays.asList(card2, card1);
    }

    private List<Card> setupSortedCards() {

        Card card1 = setupCard(setupCardRequest());
        Card card2 = setupCard(setupCardRequest2());

        return Arrays.asList(card1, card2);
    }

    @Test
    public void saveSuccessTest() {

        CardRequest request = setupCardRequest();
        Card card = setupCard(request);

        Activity activity = setupActivity();

        when(activityService.findByActivityId((long)1)).thenReturn(activity);
        when(repository.save(any(Card.class))).thenReturn(card);

        assertEquals(card, service.save(request));
    }

    @Test
    public void saveFailTest() {

        CardRequest request = setupCardRequest();
        request.setActivityId(null);

        assertNull(service.save(request));
    }

    @Test
    public void findByActivityIdSuccessTest() {
        long activityId = 1;

        Activity activity = setupActivity();
        Card card = setupCard(setupCardRequest());
        List<Card> cards = Collections.singletonList(card);

        when(activityService.findByActivityId(activityId)).thenReturn(activity);
        when(repository.findByActivity(activity)).thenReturn(cards);

        assertIterableEquals(cards, service.findByActivityId(activityId));
    }

    @Test
    public void findByActivityIdFailTest() {
        long activityId = 2;

        List<Card> cards = Collections.emptyList();

        when(activityService.findByActivityId(activityId)).thenReturn(null);
        when(repository.findByActivity(null)).thenReturn(cards);

        assertIterableEquals(cards, service.findByActivityId(activityId));
    }

    @Test
    public void findByVisitIdSuccessTest() {
        long visitId = 266684;

        Card card = setupCard(setupCardRequest());
        List<Card> cards = Collections.singletonList(card);

        when(repository.findByVisitId(visitId)).thenReturn(cards);

        assertIterableEquals(cards, service.findByVisitId(visitId));
    }

    @Test
    public void findByVisitIdFailTest() {
        long visitId = 1111111;

        List<Card> cards = Collections.emptyList();

        when(repository.findByVisitId(visitId)).thenReturn(cards);

        assertIterableEquals(cards, service.findByVisitId(visitId));
    }

    @Test
    public void findByBillIdSuccessTest() {
        long billId = 1;

        Bill bill = setupBill();
        Card card = setupCard(setupCardRequest());
        List<Card> cards = Collections.singletonList(card);

        when(billService.findByBillId(billId)).thenReturn(bill);
        when(repository.findByBill(bill)).thenReturn(cards);

        assertIterableEquals(cards, service.findByBillId(billId));
    }

    @Test
    public void findByBillIdFailTest() {
        long billId = 0;

        List<Card> cards = Collections.emptyList();

        when(billService.findByBillId(billId)).thenReturn(null);
        when(repository.findByBill(null)).thenReturn(cards);

        assertIterableEquals(cards, service.findByBillId(billId));
    }

    @Test
    public void findByPatientNameSuccessTest() {
        String name = "Fernando Leite Serrano";

        Patient patient = setupPatient();
        Card card = setupCard(setupCardRequest());
        List<Card> cards = Collections.singletonList(card);

        when(patientService.findByName(name)).thenReturn(patient);
        when(repository.findByPatient(patient)).thenReturn(cards);

        assertIterableEquals(cards, service.findByPatientName(name));
    }

    @Test
    public void findByPatientNameFailTest() {
        String name = "Breno José Bueno Otsuka";

        List<Card> cards = Collections.emptyList();

        when(patientService.findByName(name)).thenReturn(null);
        when(repository.findByPatient(null)).thenReturn(cards);

        assertIterableEquals(cards, service.findByPatientName(name));
    }

    @Test
    public void filterByPriorityTest() {

        List<Card> cards = setupUnsortedCards();
        List<Card> answer = setupSortedCards();

        List<Card> priority = service.filterByPriority(cards);

        assertTrue(priority.size() == answer.size() &&
                priority.get(0).getDaysSinceCreated() == answer.get(0).getDaysSinceCreated() &&
                priority.get(1).getDaysSinceCreated() == answer.get(1).getDaysSinceCreated());
    }

    @Test
    public void filterByToReceiveTest() {
        List<Card> cards = setupUnsortedCards();
        Card card = setupCard(setupCardRequest());
        List<Card> answer = Collections.singletonList(card);

        List<Card> toReceive = service.filterByToReceive(cards);

        String expectedName = answer.get(0).getPatient().getName();
        String actualName   = toReceive.get(0).getPatient().getName();

        assertTrue(toReceive.size() == 1 && actualName.equals(expectedName));
    }

    @Test
    public void filterByToSendTest() {
        List<Card> cards = setupUnsortedCards();
        Card card = setupCard(setupCardRequest2());
        List<Card> answer = Collections.singletonList(card);

        List<Card> toReceive = service.filterByToSend(cards);

        String expectedName = answer.get(0).getPatient().getName();
        String actualName   = toReceive.get(0).getPatient().getName();

        assertTrue(toReceive.size() == 1 && actualName.equals(expectedName));
    }
}
