package UI;

import classes.Organizm;
import classes.Swiat;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {

    private int TILE_SIZE = 16; // size of each square
    private static final Color LIGHT_GREEN = new Color(85, 111, 68); // light green
    private static final Color DARK_GREEN = new Color(101, 155, 94);      // dark green
    private Swiat swiat;


    public WorldPanel(int TILE_SIZE) {
        setTILE_SIZE(TILE_SIZE);
        setLayout(new FlowLayout()); // add components on top
    }

    public void setSwiat(Swiat swiat) {
        this.swiat = swiat;
    }

    public void redraw(){
        swiat.wykonajTure();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < 740; y += TILE_SIZE) {
            for (int x = 0; x < 580; x += TILE_SIZE) {
                boolean isLight = ((x / TILE_SIZE + y / TILE_SIZE) % 2 == 0);
                g.setColor(isLight ? LIGHT_GREEN : DARK_GREEN);
                g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
            }
        }
        for(Organizm organizm : swiat.getOrganizmy()) {
            g.setColor(Color.DARK_GRAY);
            String symbol = String.valueOf(organizm.rysowanie());
            drawStringInTile(g,symbol,organizm.getPozycjaX()-1,organizm.getPozycjaY()-1,TILE_SIZE);
        }
    }

    public void drawStringInTile(Graphics g, String text, int xTile, int yTile, int tileSize) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font font = new Font("Segoe UI Emoji", Font.PLAIN, tileSize / 2);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics(font);

        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getAscent(); // distance from baseline to top

        int xPixel = xTile * tileSize + (tileSize - textWidth) / 2;
        int yPixel = yTile * tileSize + (tileSize + textHeight) / 2 - 2;

        g2.drawString(text, xPixel, yPixel);
    }

    private void setTILE_SIZE(int TILE_SIZE) {
        this.TILE_SIZE = TILE_SIZE;
    }
}
