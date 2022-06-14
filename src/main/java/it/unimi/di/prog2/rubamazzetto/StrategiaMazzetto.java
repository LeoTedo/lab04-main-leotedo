package it.unimi.di.prog2.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.Iterator;
import java.util.List;

public class StrategiaMazzetto implements SelettoreCarta{
    private SelettoreCarta next;
    private Giocatore giocatore;

    public StrategiaMazzetto(SelettoreCarta next, Giocatore giocatore) {
        this.next = next;
        this.giocatore = giocatore;
    }


    @Override
    public Card getCard(List<Card> mano) {
        for (Card card: mano){
            Iterator<Giocatore> altri = giocatore.getAltri();
            while (altri.hasNext()){
                Giocatore g = altri.next();
                if (g.getMazzettoTop() == card.getRank()){
                    return card;
                }
            }
        }
        return next.getCard(mano);
    }

}
