package classes.animals;

import classes.Organizm;
import classes.Swiat;
import classes.Zwierze;

public class Czlowiek extends Zwierze {

    private boolean umiejetnoscAktywna = false;
    private int dlugoscUmiejetnosci = 0;
    private int dlugoscRegeneracji = 0;

    public Czlowiek(int x, int y, Swiat swiat) {
        super(5, 4, x, y, swiat);
    }

    @Override
    public String nazwa() {
        return "Czlowiek";
    }

    // Gettery
    public boolean isUmiejetnoscAktywna() {
        return umiejetnoscAktywna;
    }

    public int getDlugoscUmiejetnosci() {
        return dlugoscUmiejetnosci;
    }

    public int getDlugoscRegeneracji() {
        return dlugoscRegeneracji;
    }

    // Settery
    public void setUmiejetnoscAktywna(boolean umiejetnoscAktywna) {
        this.umiejetnoscAktywna = umiejetnoscAktywna;
    }

    public void setDlugoscUmiejetnosci(int dlugoscUmiejetnosci) {
        this.dlugoscUmiejetnosci = dlugoscUmiejetnosci;
    }

    public void setDlugoscRegeneracji(int dlugoscRegeneracji) {
        this.dlugoscRegeneracji = dlugoscRegeneracji;
    }

    @Override
    public Organizm dodajPotomka(int x, int y) {
        return null; // Tylko jeden człowiek może istnieć
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy) {
        if (umiejetnoscAktywna) {
            getSwiat().nowyLog("Czlowiek odbija atak przy pomocy Tarczy Azura!");
            return true;
        }
        return false;
    }

    @Override
    public void akcja() {
        // Obsługa umiejętności
        if (dlugoscUmiejetnosci > 0) {
            dlugoscUmiejetnosci--;
        }

        if (dlugoscRegeneracji > 0) {
            umiejetnoscAktywna = false;
            dlugoscRegeneracji--;
            getSwiat().nowyLog("Umiejetnosc regeneruje sie!");
        }

        if (dlugoscUmiejetnosci == 0 && umiejetnoscAktywna) {
            umiejetnoscAktywna = false;
            dlugoscRegeneracji = 5;
            getSwiat().nowyLog("Umiejetnosc przestala dzialac!");
        }

        // Pobranie komendy od gracza
        int fromX = getPozycjaX();
        int fromY = getPozycjaY();
        int moveX = fromX;
        int moveY = fromY;

        int akcja = getSwiat().getCommand();

        switch (akcja) {
            case 'w' -> moveY--;
            case 's' -> moveY++;
            case 'd' -> moveX++;
            case 'a' -> moveX--;
            case '3' -> {
                if (umiejetnoscAktywna) {
                    getSwiat().nowyLog("Umiejetnosc juz aktywna!");
                } else if (dlugoscRegeneracji > 0) {
                    getSwiat().nowyLog("Umiejetnosc bedzie dostepna za " + dlugoscRegeneracji + " tur!");
                } else {
                    umiejetnoscAktywna = true;
                    dlugoscUmiejetnosci = 5;
                    getSwiat().nowyLog("Tarcza Azura aktywowana! (5 tur)");
                }
            }
            default -> {}
        }

        // Jeżeli brak ruchu
        if (fromX == moveX && fromY == moveY) return;

        // Ruch
        Organizm other = getSwiat().findOrganismAt(moveX, moveY);
        if (other == null && isInBounds(getSwiat().getSettings(),moveY,moveX)) {
            setPozycja(moveX, moveY);
            getSwiat().nowyLog("Czlowiek przesuwa sie na x:" + moveX + " y:" + moveY);
        } else if (other != null) {
            kolizja(fromX, fromY, other);
        }
    }

    @Override
    public void kolizja(int fromX, int fromY, Organizm other) {
        super.kolizja(fromX, fromY, other);
        // Możesz dodać dodatkowe efekty/specjalne interakcje
    }
}

