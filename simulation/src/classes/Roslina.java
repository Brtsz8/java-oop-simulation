package classes;

import java.util.Random;

public abstract class Roslina extends Organizm{
    private static final Random random = new Random();

    //konstruktor z inicjatywa 0
    public Roslina(int sila, int pozycjaX, int pozycjaY,Swiat swiat) {
        super(sila,0,pozycjaX,pozycjaY,swiat);
    }

    //kazda roslina musi to zaimplementowac
    public abstract Organizm dodajPotomka(int x, int y);

    //czy rozrasta sie 33% szans
    protected boolean czyRosnie() {
        return random.nextInt(10)%3 == 0;
    }

    @Override
    public void akcja() {
        if (!czyRosnie()) return;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int dir = getRandomDir();
        int newX = getPozycjaX() + dx[dir];
        int newY = getPozycjaY() + dy[dir];

        if (!isInBounds(getSwiat().getSettings(),newY, newX)) return;

        if (getSwiat().findOrganismAt(newX, newY) == null) {

            Organizm potomek = this.dodajPotomka(newX, newY);
            if (potomek == null) {
                System.err.println("[BŁĄD] Nie udało się stworzyć potomka rośliny!");
                return;
            }
            getSwiat().nowyOrganizm(potomek);
            getSwiat().nowyLog(nazwa() + " rozprzestrzenia się na x:" + newX + " y:" + newY);
        }
    }


    // Domyślnie roślina nie reaguje na kolizję (chyba że nadpisana np. w WilczeJagody)
    @Override
    public void kolizja(int fromX, int fromY, Organizm other) {
        // Nic nie robi
    }
}
