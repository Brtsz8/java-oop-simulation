package UI;

import classes.Organizm;
import classes.Swiat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorldPanel extends JPanel {

    private int TILE_SIZE = 16; // size of each square
    private static final Color LIGHT_GREEN = new Color(85, 111, 68); // light green
    private static final Color DARK_GREEN = new Color(101, 155, 94);      // dark green
    private Swiat swiat;

    private List<BufferedImage> images = new ArrayList<>();


    public WorldPanel(int TILE_SIZE) {
        setTILE_SIZE(TILE_SIZE);
        setLayout(new FlowLayout()); // add components on top

        //loads images in
        BufferedImage imgWilk = null;
        BufferedImage imgOwca = null;
        BufferedImage imgCzlowiek = null;
        BufferedImage imgAntylopa = null;
        BufferedImage imgZolw = null;
        BufferedImage imgLis = null;
        BufferedImage imgBarszcz = null;
        BufferedImage imgGuarana = null;
        BufferedImage imgJagody = null;
        BufferedImage imgMlecz = null;
        BufferedImage imgTrawa = null;
        try {
            imgWilk = ImageIO.read(new File("src/images/wilk.png"));
            imgOwca = ImageIO.read(new File("src/images/owca.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(ImagesEnum imageNum : ImagesEnum.values()){

            images.add(imgWilk);
            images.add(imgOwca);
        }
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
        BufferedImage emojiImage = null;
        try {
            System.out.println("Looking for: " + new File("src/images/owca.png").getAbsolutePath());

            emojiImage = ImageIO.read(new File("src/images/wilk.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int y = 0; y < 740; y += TILE_SIZE) {
            for (int x = 0; x < 580; x += TILE_SIZE) {
                boolean isLight = ((x / TILE_SIZE + y / TILE_SIZE) % 2 == 0);
                g.setColor(isLight ? LIGHT_GREEN : DARK_GREEN);
                g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
            }
        }
        for(Organizm organizm : swiat.getOrganizmy()) {
            String nazwa = organizm.nazwa();
            if(Objects.equals(nazwa, "Wilk"))
                emojiImage = images.get(ImagesEnum.WILK.ordinal());
            if(Objects.equals(nazwa, "Owca"))
                emojiImage = images.get(ImagesEnum.OWCA.ordinal());

            g.drawImage(emojiImage,
                    (organizm.getPozycjaX()-1)*TILE_SIZE,
                    (organizm.getPozycjaY()-1)*TILE_SIZE
                    ,TILE_SIZE,TILE_SIZE, null);
        }
    }

    private void setTILE_SIZE(int TILE_SIZE) {
        this.TILE_SIZE = TILE_SIZE;
    }
}
