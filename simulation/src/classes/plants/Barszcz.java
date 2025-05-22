package classes.plants;

import classes.Organizm;
import classes.Roslina;
import classes.Swiat;
import classes.Zwierze;

public class Barszcz extends Roslina {

    public Barszcz(int x, int y, Swiat swiat) {
        super(99, x, y, swiat);
    }

    @Override
    public String nazwa() {
        return "Barszcz";
    }

    @Override
    public Organizm dodajPotomka(int x, int y) {
        return new Barszcz(x, y, getSwiat());
    }

    @Override
    public void kolizja(int fromX, int fromY, Organizm other) {
        String log = nazwa() + " został zjedzony - " + other.nazwa() + " ginie";
        other.setSila(-1); // Zabija atakujący organizm
        getSwiat().nowyLog(log);
        setZyjeFalse();
    }

    @Override
    public void wplywNaSile(Organizm atakujacy) {
        String log = nazwa() + " został zjedzony - " + atakujacy.nazwa() + " ginie";
        getSwiat().nowyLog(log);
        atakujacy.setSila(-1); // Zabija atakującego
    }

    @Override
    public void akcja() {
        int x = getPozycjaX();
        int y = getPozycjaY();

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int i = 0; i < 4; ++i) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (isInBounds(getSwiat().getSettings(), newY,newX)) {
                Organizm sasiad = getSwiat().findOrganismAt(newX, newY);
                if (sasiad != null && sasiad instanceof Zwierze) {
                    String log = nazwa() + " zabija " + sasiad.nazwa() + " który był za blisko";
                    getSwiat().nowyLog(log);
                    sasiad.setZyjeFalse();
                }
            }
        }

        super.akcja(); // Kontynuuje standardowe rozprzestrzenianie się rośliny
    }
}
