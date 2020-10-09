package br.com.brenootsuka.pegcontas.model.response;

import br.com.brenootsuka.pegcontas.model.Card;

import java.util.List;

public class CardResponse {

    private List<Card> cards;

    public CardResponse(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
