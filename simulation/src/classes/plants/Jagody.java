package classes.plants;

import classes.Organizm;
import classes.Roslina;
import classes.Swiat;

public class Jagody extends Roslina {

    public Jagody(int x, int y, Swiat swiat) {
        super(10, x, y, swiat);
    }

    @Override
    public String rysowanie() {
        return "%";
    }

    @Override
    public String nazwa() {
        return "Jagody";
    }

    @Override
    public Organizm dodajPotomka(int x, int y) {
        return new Jagody(x, y, getSwiat());
    }

    @Override
    public void kolizja(int fromX, int fromY, Organizm other) {
        String log = nazwa() + " zjedzone - ginie " + other.nazwa() +
                " na polu x:" + other.getPozycjaX() + " y:" + other.getPozycjaY();
        other.setSila(-1); // Zabić atakującego
        getSwiat().nowyLog(log);
    }

    @Override
    public void wplywNaSile(Organizm atakujacy) {
        String log = nazwa() + " zjedzone - ginie " + atakujacy.nazwa() +
                " na polu x:" + atakujacy.getPozycjaX() + " y:" + atakujacy.getPozycjaY();
        getSwiat().nowyLog(log);
        atakujacy.setSila(-1); // Zabija atakującego
        this.setZyjeFalse(); // Usunąć siebie z planszy
    }
}
