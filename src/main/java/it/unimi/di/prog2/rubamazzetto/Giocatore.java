package it.unimi.di.prog2.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Giocatore {

  private final String nome;
  private List<Card> mano = new ArrayList<>();
  private Rank mazzettoTop;
  private List<Giocatore> altri = new ArrayList<>();
  private int punti;
  private SelettoreCarta strategia; //TODO definire opportunamente l'interfaccia

  public Giocatore(String nome) {
    this.nome = nome;
  }

  public Rank getMazzettoTop() {
    return mazzettoTop;
  }

  public Iterator<Giocatore> getAltri() {
    return altri.iterator();
  }

  public void addAltro(Giocatore g) {
    if (!altri.contains(g))
      altri.add(g);
  }

  public int getPunti() {
    return punti;
  }

  public void daiCarta(Card carta) {
    mano.add(carta);
  }

  public void setStrategia(SelettoreCarta strategia) {
    this.strategia = strategia;
  }


  public void turno() {
    assert strategia != null;
    final Card aCard = strategia.getCard(mano);

    mano.remove(aCard);
    if (Partita.TAVOLO.inMostra(aCard)) {
      this.prendiDalTavolo(aCard);
      return;
    }
    for (Giocatore giocatore : altri) {
      if (sePuoiRuba(giocatore, aCard)) return;
    }
    Partita.TAVOLO.metti(aCard);
  }

private boolean sePuoiRuba(Giocatore altro, Card aCard){
    if (altro.getMazzettoTop() == aCard.getRank()){
      punti += altro.cediMazzetto() + 1;
      mazzettoTop = aCard.getRank();
      return true;
    }
    return false;
}

  private int cediMazzetto() {
    int ris = getPunti();
    punti = 0;
    mazzettoTop = null;
    return ris;
  }

  private void prendiDalTavolo(Card aCard) {
    Partita.TAVOLO.prendi(aCard);
    punti += 2;
    mazzettoTop = aCard.getRank();
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder(nome);
    s.append(": ");
    s.append("[").append(mano.size()).append("]");
    if (punti > 0) {
      s.append("mazzetto con ");
      s.append(punti);
      s.append(" carte, cima ");
      s.append(mazzettoTop);
      s.append("; ");
    }
    for (Card card : mano) {
      s.append(card.toString());
      s.append(", ");
    }
    return s.toString();
  }

  public int numCards() {
    return mano.size();
  }
}
