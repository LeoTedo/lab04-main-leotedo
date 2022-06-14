package it.unimi.di.prog2.rubamazzetto;

import java.util.ArrayList;
import java.util.List;

public class Rubamazzetto {
  public static void main(String[] args) {
    List<Giocatore> partecipanti = new ArrayList<>();

 Giocatore dybala = new Giocatore("Dybala");
 Giocatore messi = new Giocatore("Messi");
 Giocatore cristiano = new Giocatore("Cristiano");
    partecipanti.add(dybala);
    partecipanti.add(messi);
    partecipanti.add(cristiano);

    dybala.setStrategia(new StrategiaCartaTavolo(new StrategiaCasuale()));
    messi.setStrategia(new StrategiaMazzetto(new StrategiaCartaTavolo(new StrategiaCasuale()), cristiano));
    cristiano.setStrategia(new StrategiaCasuale());

    Partita p = new Partita(partecipanti);

    p.distribuisciMano(3);
    while (!p.isFinita()){

      System.out.println(p);
      for (Giocatore giocatore : partecipanti) {
        giocatore.turno();
      }
      p.distribuisciMano(1);
      System.out.println(p);

    }

  }
}
