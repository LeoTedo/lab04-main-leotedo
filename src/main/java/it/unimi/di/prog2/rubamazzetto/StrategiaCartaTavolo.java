package it.unimi.di.prog2.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;

public class StrategiaCartaTavolo implements SelettoreCarta{
    private final SelettoreCarta next;

    public StrategiaCartaTavolo(SelettoreCarta next) {
        this.next = next;
    }

    @Override
    public Card getCard(List<Card> mano) {
        for (Card card: mano) {
            if (Partita.TAVOLO.inMostra(card)) {
                return card;
            }
        }
            return next.getCard(mano);
    }
}
