package classes.plants;

import classes.Organizm;
import classes.Roslina;
import classes.Swiat;

public class Mlecz extends Roslina {

    public Mlecz(int x, int y, Swiat swiat) {
        super(0, x, y, swiat);
    }

    @Override
    public String rysowanie() {
        return "@";
    }

    @Override
    public String nazwa() {
        return "Mlecz";
    }

    @Override
    public Organizm dodajPotomka(int x, int y) {
        return new Mlecz(x, y, getSwiat());
    }

    @Override
    public void akcja() {
        int proby = 3;
        while (proby-- > 0) {
            if (!this.czyRosnie())
                continue;

            int[] moveX = {0, 0, -1, 1};
            int[] moveY = {-1, 1, 0, 0};

            int dir = getRandomDir() % 4; // Ogranicz kierunek do 0–3
            int fromX = getPozycjaX();
            int fromY = getPozycjaY();

            int newX = fromX + moveX[dir];
            int newY = fromY + moveY[dir];

            if (!isInBounds(getSwiat().getSettings(), newY, newX))
                continue;

            Organizm other = getSwiat().findOrganismAt(newX, newY);
            if (other == null) {
                Organizm potomek = this.dodajPotomka(newX, newY);
                if (potomek != null) {
                    getSwiat().nowyOrganizm(potomek);
                    getSwiat().nowyLog("Dodano nowy " + potomek.nazwa() + " na x:" + newX + " y:" + newY);
                } else {
                    System.err.println("Nie udało się stworzyć potomka – błąd programu.");
                }
            }
        }
    }
}
