package br.com.brenootsuka.pegcontas.model.response;

import br.com.brenootsuka.pegcontas.commons.enums.SlaStatus;
import br.com.brenootsuka.pegcontas.model.Card;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CardResponse {

    private List<Card> cards;
    private int totalCardsOk;
    private int totalCardsWarning;
    private int totalCardsDelayed;

    public CardResponse(List<Card> cards) {

        this.cards = cards;
        this.totalCardsOk = cards.stream().filter(
                card -> card.getSlaStatus() == SlaStatus.OK).collect(Collectors.toList()
        ).size();
        this.totalCardsWarning = cards.stream().filter(
                card -> card.getSlaStatus() == SlaStatus.WARNING).collect(Collectors.toList()
        ).size();
        this.totalCardsDelayed = cards.stream().filter(
                card -> card.getSlaStatus() == SlaStatus.DELAYED).collect(Collectors.toList()
        ).size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getTotalCardsOk() {
        return totalCardsOk;
    }

    public void setTotalCardsOk(int totalCardsOk) {
        this.totalCardsOk = totalCardsOk;
    }

    public int getTotalCardsWarning() {
        return totalCardsWarning;
    }

    public void setTotalCardsWarning(int totalCardsWarning) {
        this.totalCardsWarning = totalCardsWarning;
    }

    public int getTotalCardsDelayed() {
        return totalCardsDelayed;
    }

    public void setTotalCardsDelayed(int totalCardsDelayed) {
        this.totalCardsDelayed = totalCardsDelayed;
    }
}
