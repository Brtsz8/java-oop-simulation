package classes.animals;

import classes.Organizm;
import classes.Swiat;
import classes.Zwierze;

public class Lis extends Zwierze {
    public Lis(int pozycjaX, int pozycjaY, Swiat swiat) {
        super(3, 7,pozycjaX,pozycjaY,swiat);
    }

    @Override
    public char rysowanie() {
        return 'L';
    }

    @Override
    public String nazwa() {
        return "Lis";
    }

    @Override
    public Organizm dodajPotomka(int x, int y) {
        return new Lis(x,y,getSwiat());
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
        }else if (wiekszaSilaOd(other)){
            kolizja(fromX, fromY, other);
        } else {
            log.append(nazwa()).append(" nie rusza się na pole x: ").append(newX)
                    .append(" y: ").append(newY).append(" - jest słabszy! ");
        }
        getSwiat().nowyLog(log.toString());
    }

}
