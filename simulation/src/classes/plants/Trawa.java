package classes.plants;

import classes.Organizm;
import classes.Roslina;
import classes.Swiat;

public class Trawa extends Roslina {
    public Trawa(int pozycjaX, int pozycjaY, Swiat swiat) {
        super(0, pozycjaX, pozycjaY, swiat);
    }

    @Override
    public String nazwa() {return "Trawa";}

    @Override
    public Organizm dodajPotomka(int x, int y) {
        return new Trawa(x, y, getSwiat());
    }
}
