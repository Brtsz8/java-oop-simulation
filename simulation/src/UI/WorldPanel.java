package UI;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {

    private int TILE_SIZE = 16; // size of each square
    private static final Color LIGHT_GREEN = new Color(85, 111, 68); // light green
    private static final Color DARK_GREEN = new Color(101, 155, 94);      // dark green


    public WorldPanel(int TILE_SIZE) {
        setTILE_SIZE(TILE_SIZE);
        setLayout(new FlowLayout()); // add components on top
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
    }

    private void setTILE_SIZE(int TILE_SIZE) {
        this.TILE_SIZE = TILE_SIZE;
    }
}
