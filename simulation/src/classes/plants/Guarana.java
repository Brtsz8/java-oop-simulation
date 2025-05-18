package classes.plants;

import classes.Organizm;
import classes.Roslina;
import classes.Swiat;

public class Guarana extends Roslina {

    public Guarana(int x, int y, Swiat swiat) {
        super(0, x, y, swiat);
    }

    @Override
    public String rysowanie() {
        return "&";
    }

    @Override
    public String nazwa() {
        return "Guarana";
    }

    @Override
    public Organizm dodajPotomka(int x, int y) {
        return new Guarana(x, y, getSwiat());
    }

    @Override
    public void kolizja(int fromX, int fromY, Organizm other) {
        String log = other.nazwa() + " na polu x:" + getPozycjaX() + " y:" + getPozycjaY()
                + " ma większą siłę (" + nazwa() + ")";
        other.setSila(other.getSila() + 3); // Zwiększa siłę atakującego o 3
        getSwiat().nowyLog(log);
    }

    @Override
    public void wplywNaSile(Organizm atakujacy) {
        String log = atakujacy.nazwa() + " na polu x:" + getPozycjaX() + " y:" + getPozycjaY()
                + " ma większą siłę (" + nazwa() + ")";
        getSwiat().nowyLog(log);
        atakujacy.setSila(atakujacy.getSila() + 3); // Wzrost siły organizmu po zjedzeniu
        this.setZyjeFalse(); // Roślina ginie
    }
}
