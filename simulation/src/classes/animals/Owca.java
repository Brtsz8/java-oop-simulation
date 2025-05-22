package classes.animals;

import classes.Organizm;
import classes.Swiat;
import classes.Zwierze;

public class Owca extends Zwierze {

    //konstruktor
    public Owca(int pozycjaX, int pozycjaY, Swiat swiat) {
        //sila i inicjatywa = 4
        super(4, 4, pozycjaX, pozycjaY, swiat);
    }

    @Override
    public String nazwa() {
        return "Owca";
    }

    @Override
    public Organizm dodajPotomka(int x, int y) {
         return new Owca(x,y,getSwiat());
    }
}
