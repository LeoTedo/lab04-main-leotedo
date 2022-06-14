package it.unimi.di.prog2.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;
import java.util.Random;

public class StrategiaCasuale implements SelettoreCarta{

    @Override
    public Card getCard(List<Card> mano) {
        Random random = new Random();
        int i = random.nextInt(mano.size());
        return mano.get(i);
    }
}
