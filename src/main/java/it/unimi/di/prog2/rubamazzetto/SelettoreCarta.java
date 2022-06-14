package it.unimi.di.prog2.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;

public interface SelettoreCarta {
   Card getCard(List<Card> mano);
}
