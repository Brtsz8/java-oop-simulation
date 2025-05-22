package classes.animals;

import classes.Organizm;
import classes.Swiat;
import classes.Zwierze;

public class Zolw extends Zwierze {

    public Zolw(int pozycjaX, int pozycjaY, Swiat swiat) {
        super(2, 1, pozycjaX, pozycjaY, swiat); // Siła = 2, Inicjatywa = 1
    }

    @Override
    public void akcja() {
        // 75% szans na brak ruchu (losowana liczba 0-3, tylko dla 0 ruch)
        if (getRandomDir() == 0) {
            super.akcja();
        }
    }

    @Override
    public String nazwa() {
        return "Zolw";
    }

    @Override
    public Organizm dodajPotomka(int x, int y) {
        return new Zolw(x, y, getSwiat());
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy) {
        return atakujacy.getSila() < 5;
    }
}
