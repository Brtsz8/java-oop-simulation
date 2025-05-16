package classes.animals;

import classes.Organizm;
import classes.Swiat;
import classes.Zwierze;

public class Wilk extends Zwierze{
    public Wilk(int pozycjaX, int pozycjaY, Swiat swiat){
        super(9,5,pozycjaX,pozycjaY,swiat);
    }

    @Override
    public char rysowanie() {
        return 'W';
    }

    @Override
    public String nazwa() {
        return "Wilk";
    }

    @Override
    public Organizm dodajPotomka(int x, int y) {
        return new Wilk(x,y,getSwiat());
    }
}
