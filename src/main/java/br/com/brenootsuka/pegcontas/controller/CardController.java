package br.com.brenootsuka.pegcontas.controller;

import br.com.brenootsuka.pegcontas.model.Card;
import br.com.brenootsuka.pegcontas.model.request.CardRequest;
import br.com.brenootsuka.pegcontas.model.response.CardResponse;
import br.com.brenootsuka.pegcontas.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/card")
public class CardController {

    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<CardResponse> findBy(
            @RequestParam String q,
            @RequestParam String filter,
            @RequestParam String value
    ) {
        List<Card> cards = Collections.emptyList();

        switch (q) {
            case "activityId":
                cards = cardService.findByActivityId(Long.valueOf(value));
                break;
            case "visitId":
                cards = cardService.findByVisitId(Long.valueOf(value));
                break;
            case "billId":
                cards = cardService.findByBillId(Long.valueOf(value));
                break;
            case "patientName":
                cards = cardService.findByPatientName(value);
                break;
            default: break;
        }

        switch (filter) {
            case "PRIORITY":
                cards = cardService.filterByPriority(cards);
                break;
            case "TO_RECEIVE":
                cards = cardService.filterByToReceive(cards);
                break;
            case "TO_SEND":
                cards = cardService.filterByToSend(cards);
                break;
            default: break;
        }

        CardResponse response = new CardResponse(cards);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Card> save(@Valid @RequestBody CardRequest request) {

        Card card = cardService.save(request);

        return new ResponseEntity<>(card, HttpStatus.OK);
    }
}
