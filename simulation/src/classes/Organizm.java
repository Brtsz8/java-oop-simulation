package classes;
import mainFiles.Settings;
import java.util.Random;

public abstract class Organizm {
    private int sila;
    private int inicjatywa;
    private int pozycjaX;
    private int pozycjaY;
    private boolean zyje = true;
    private Swiat swiat;
    private static final Random random = new Random();

    // constructor
    public Organizm(int sila, int inicjatywa, int pozycjaX, int pozycjaY, Swiat swiat) {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.pozycjaX = pozycjaX;
        this.pozycjaY = pozycjaY;
        this.swiat = swiat;
    }

    //getters
    public int getSila() { return sila; }
    public int getInicjatywa() { return inicjatywa; }
    public int getPozycjaX() { return pozycjaX; }
    public int getPozycjaY() { return pozycjaY; }
    public Swiat getSwiat() { return  swiat; }
    public boolean getZyje() { return zyje; }

    // Setters
    public void setSila(int newSila) { this.sila = newSila; }
    public void setInicjatywa(int newInicjatywa) { this.inicjatywa = newInicjatywa; }
    public void setPozycja(int newX, int newY) {
        this.pozycjaX = newX;
        this.pozycjaY = newY;
    }
    public void setZyjeFalse() { this.zyje = false; }
    public boolean wiekszaSilaOd(Organizm other) {
        return this.sila >= other.getSila();
    }

    // Returns a random direction: 0 to 3 inclusive
    public int getRandomDir() {
        return random.nextInt(4);
    }

    // Checks if coordinates are within bounds of the world's window
    public boolean isInBounds(Settings settings, int y, int x) {
        int h = settings.getHeight() / settings.getTileSize();
        int w = settings.getWidth() / settings.getTileSize();
        return y > 0 && y <= h && x > 0 && x <= w;
    }

    // Finds a free space adjacent to current position
    public int[] znajdzWolnePoleObok() {
        int[][] dirs = { {-1,0}, {1,0}, {0,-1}, {0,1} };

        for (int[] dir : dirs) {
            int newX = this.pozycjaX + dir[0];
            int newY = this.pozycjaY + dir[1];

            if (isInBounds(swiat.getSettings(), newY, newX) && swiat.findOrganismAt(newX, newY) == null) {
                return new int[]{ newX, newY };
            }
        }

        return new int[]{ -1, -1 }; // no free space
    }

    // Can be overridden by subclasses
    public boolean czyOdbilAtak(Organizm atakujacy) {
        return false;
    }

    // Can be overridden by subclasses
    public void wplywNaSile(Organizm atakujacy) {
        // base version does nothing
    }
    public abstract void akcja();

    public abstract void kolizja(int fromX, int fromY, Organizm other);

    public String nazwa() {
        return this.getClass().getSimpleName();
    }
}
