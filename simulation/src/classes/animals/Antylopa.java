package classes.animals;

import classes.Organizm;
import classes.Swiat;
import classes.Zwierze;

import java.util.Random;

public class Antylopa extends Zwierze {

    private static final Random rand = new Random();

    public Antylopa(int pozycjaX, int pozycjaY, Swiat swiat) {
        super(4, 4, pozycjaX, pozycjaY, swiat); // siła 4, inicjatywa 4
    }

    @Override
    public char rysowanie() {
        return 'A';
    }

    @Override
    public String nazwa() {
        return "Antylopa";
    }

    @Override
    public Organizm dodajPotomka(int x, int y) {
        return new Antylopa(x, y, getSwiat());
    }

    @Override
    public int getRandomDir() {
        return rand.nextInt(12); // 0 - 11
    }

    private boolean czyUcieka() {
        return getRandomDir() % 2 == 0;
    }

    @Override
    public void akcja() {
        int[] dx = {0, 0, -1, 1, -2, 2, 0, 0, 1, 1, -1, -1};
        int[] dy = {-1, 1, 0, 0, 0, 0, -2, 2, 1, -1, 1, -1};

        int dir = getRandomDir();
        int newX = getPozycjaX() + dx[dir];
        int newY = getPozycjaY() + dy[dir];

        if (!isInBounds(getSwiat().getSettings(),newY, newX)) return;

        Organizm other = getSwiat().findOrganismAt(newX, newY);

        if (other == null) {
            setPozycja(newX, newY);
            getSwiat().nowyLog(nazwa() + " przesuwa się na nową pozycję x:" + newX + ", y:" + newY);
        } else {
            kolizja(getPozycjaX(), getPozycjaY(), other);
        }
    }

    @Override
    public void kolizja(int fromX, int fromY, Organizm other) {
        if (this.getClass().equals(other.getClass())) {
            super.kolizja(fromX, fromY, other);
            return;
        }

        if (!czyUcieka()) {
            getSwiat().nowyLog(nazwa() + " na x:" + getPozycjaX() + " y:" + getPozycjaY() + " nie ucieka");
            super.kolizja(fromX, fromY, other);
        } else {
            int[] dx = {0, 0, -1, 1};
            int[] dy = {-1, 1, 0, 0};

            for (int i = 0; i < 4; i++) {
                int newX = getPozycjaX() + dx[i];
                int newY = getPozycjaY() + dy[i];

                if (isInBounds(getSwiat().getSettings(), newY, newX) &&
                        getSwiat().findOrganismAt(newX, newY) == null) {
                    setPozycja(newX, newY);
                    getSwiat().nowyLog(nazwa() + " ucieka na pole x:" + newX + " y:" + newY);
                    return;
                }
            }

            getSwiat().nowyLog(nazwa() + " próbuje uciec, ale brak wolnych pól");
            super.kolizja(fromX, fromY, other);
        }
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy) {
        if (!czyUcieka()) {
            getSwiat().nowyLog(nazwa() + " na x:" + getPozycjaX() + " y:" + getPozycjaY() + " nie ucieka");
            return false;
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int newX = getPozycjaX() + dx[i];
            int newY = getPozycjaY() + dy[i];

            if (isInBounds(getSwiat().getSettings(), newY, newX) &&
                    getSwiat().findOrganismAt(newX, newY) == null) {
                setPozycja(newX, newY);
                getSwiat().nowyLog(nazwa() + " ucieka na pole x:" + newX + " y:" + newY);
                return true;
            }
        }

        return false; // nie ma gdzie uciec
    }
}
