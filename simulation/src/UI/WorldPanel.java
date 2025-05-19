package UI;

import classes.Organizm;
import classes.Swiat;
import classes.animals.Czlowiek;

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
    private static final Color LIGHT_GREEN = new Color(20, 153, 17); // light green
    private static final Color DARK_GREEN = new Color(37, 109, 27);      // dark green
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
        BufferedImage imgTarcza = null;
        try {
            //WILK,OWCA,ZOLW,LIS,CZLOWIEK,ANTYLOPA,BARSZCZ,GUARANA,JAGODY,MLECZ,TRAWA;
            imgWilk = ImageIO.read(new File("src/images/wilk.png"));
            imgOwca = ImageIO.read(new File("src/images/owca.png"));
            imgZolw = ImageIO.read(new File("src/images/zolw.png"));
            imgLis = ImageIO.read(new File("src/images/lis.png"));
            imgCzlowiek = ImageIO.read(new File("src/images/czlowiek.png"));
            imgAntylopa = ImageIO.read(new File("src/images/antylopa.png"));
            imgBarszcz = ImageIO.read(new File("src/images/barszcz.png"));
            imgGuarana = ImageIO.read(new File("src/images/guarana.png"));
            imgJagody = ImageIO.read(new File("src/images/jagody.png"));
            imgMlecz = ImageIO.read(new File("src/images/mlecz.png"));
            imgTrawa = ImageIO.read(new File("src/images/trawa.png"));
            imgTarcza = ImageIO.read(new File("src/images/tarcza.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(ImagesEnum imageNum : ImagesEnum.values()){

            images.add(imgWilk);
            images.add(imgOwca);
            images.add(imgZolw);
            images.add(imgLis);
            images.add(imgCzlowiek);
            images.add(imgAntylopa);
            images.add(imgBarszcz);
            images.add(imgGuarana);
            images.add(imgJagody);
            images.add(imgMlecz);
            images.add(imgTrawa);
            images.add(imgTarcza);
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

        for (int y = 0; y < swiat.getSettings().getHeight(); y += TILE_SIZE) {
            for (int x = 0; x < swiat.getSettings().getWidth(); x += TILE_SIZE) {
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
            if(Objects.equals(nazwa, "Zolw"))
                emojiImage = images.get(ImagesEnum.ZOLW.ordinal());
            if(Objects.equals(nazwa, "Antylopa"))
                emojiImage = images.get(ImagesEnum.ANTYLOPA.ordinal());
            if(Objects.equals(nazwa, "LIS"))
                emojiImage = images.get(ImagesEnum.LIS.ordinal());
            if(Objects.equals(nazwa, "Czlowiek")){
                if(organizm instanceof Czlowiek && ((Czlowiek) organizm).isUmiejetnoscAktywna()){
                    emojiImage = images.get(ImagesEnum.TARCZA.ordinal());
                }else
                    emojiImage = images.get(ImagesEnum.CZLOWIEK.ordinal());
            }

            if(Objects.equals(nazwa, "Trawa"))
                emojiImage = images.get(ImagesEnum.TRAWA.ordinal());
            if(Objects.equals(nazwa, "Mlecz"))
                emojiImage = images.get(ImagesEnum.MLECZ.ordinal());
            if(Objects.equals(nazwa, "Jagody"))
                emojiImage = images.get(ImagesEnum.JAGODY.ordinal());
            if(Objects.equals(nazwa, "Guarana"))
                emojiImage = images.get(ImagesEnum.GUARANA.ordinal());
            if(Objects.equals(nazwa, "Barszcz"))
                emojiImage = images.get(ImagesEnum.BARSZCZ.ordinal());

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
