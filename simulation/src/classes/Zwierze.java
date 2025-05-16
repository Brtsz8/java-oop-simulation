package classes;

import java.sql.SQLOutput;

public abstract class Zwierze extends Organizm{

    public Zwierze(int sila, int inicjatywa, int pozycjaX, int pozycjaY, Swiat swiat) {
        super(sila, inicjatywa, pozycjaX, pozycjaY, swiat);
    }

    @Override
    public void akcja() {
        StringBuilder log = new StringBuilder();

        if (getSwiat() == null || getSwiat().getWorldPanel() == null) {
            System.err.println("Błąd: świat lub jego panel nie wczytały się!");
            throw new IllegalStateException("Świat lub Panel są null");
        }
        int dir = getRandomDir();
        int[] moveX = { 0, 0,-1, 1};
        int[] moveY = {-1, 1, 0, 0};

        int fromX = getPozycjaX();
        int fromY = getPozycjaY();

        int newX = fromX +moveX[dir];
        int newY = fromY +moveY[dir];

        if(!isInBounds(getSwiat().getSettings(),newY, newX)) return;

        Organizm other = getSwiat().findOrganismAt(newX,newY);

        if(other == null) {
            setPozycja(newX,newY);
            log.append("Przesuwam ").append(nazwa())
                    .append(" na nowa pozycje x:").append(newX)
                    .append(", y: ").append(newY);
            getSwiat().nowyLog(log.toString());
        }else {
            kolizja(fromX, fromY, other);
        }
        getSwiat().nowyLog(log.toString());
    }

    @Override
    public void kolizja(int fromX, int fromY, Organizm other) {
        StringBuilder log = new StringBuilder();

        //rozmnazanie w przypadku dwoch takich samych organizmow
        if (this.getClass() == other.getClass()) {
            int coords[] = znajdzWolnePoleObok();
            int newX = coords[0];
            int newY = coords[1];

            if (newX == -1 && newY == -1) return;
            Organizm potomek = this.dodajPotomka(newX, newY);
            if (potomek == null) {
                System.out.println("Nie udało się stworzyć potomka - błąd programu :(");
                return;
            }
            log.append(nazwa()).append(" i ").append(other.nazwa())
                    .append(" beda miec potomka na x:").append(newX)
                    .append(" y:").append(newY);
            getSwiat().nowyOrganizm(potomek);
            getSwiat().nowyLog(log.toString());
            return;
        }

        if (other.czyOdbilAtak(this)) {
            log.append(nazwa())
                    .append(" nieudany atak na x:").append(getPozycjaX())
                    .append(" y:").append(getPozycjaY())
                    .append(" - wraca na swoje pole");
            getSwiat().nowyLog(log.toString());
            setPozycja(fromX,fromY);
            return;
        }
        other.wplywNaSile(this);
        if(getSila() < 0) {
            setZyjeFalse();
            log.append(nazwa()).append(" zatruty przez ").append(other.nazwa());
            getSwiat().nowyLog(log.toString());
            return;
        }

        if (wiekszaSilaOd(other)) {
            setPozycja(other.getPozycjaX(), other.getPozycjaY());
            other.setZyjeFalse();
            log.append(nazwa()).append(" zabija ").append(other.nazwa())
                    .append(" na x:").append(other.getPozycjaX())
                    .append(" y:").append(other.getPozycjaY());
            getSwiat().nowyLog(log.toString());
        } else {
            setZyjeFalse();
            log.append(other.nazwa()).append(" zabija ").append(nazwa())
                    .append(" na x:").append(getPozycjaX())
                    .append(" y:").append(getPozycjaY());
            getSwiat().nowyLog(log.toString());
        }
    }

    //placeholdery, powinny byc nadpisane ale nie musza
    public Organizm dodajPotomka(int x, int y) {
        return null;
    }
}
