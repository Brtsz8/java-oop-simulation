package classes;

public abstract class Organizm {
    protected int x, y;
    protected boolean zyje = true;

    public int getPozycjaX() {
        return x;
    }

    public int getPozycjaY() {
        return y;
    }

    public boolean getZyje() {
        return zyje;
    }

    public abstract int getInicjatywa();
    public abstract void akcja();
    public abstract char rysowanie();
}
