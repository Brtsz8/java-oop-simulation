import UI.WorldPanel;
import classes.*;
import classes.animals.*;
import classes.plants.*;
import mainFiles.MainFrame;
import mainFiles.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        Settings worldSettings = new Settings("src/files/settings.txt");
        MainFrame mainFrame = new MainFrame(worldSettings);
        WorldPanel mainWorld = mainFrame.getWorldPanel();

        Swiat swiat = new Swiat(mainWorld, worldSettings);
        //mainFrame i mainWorld przechowuja referencje do swiata
        mainWorld.setSwiat(swiat);
        mainFrame.setSwiat(swiat);

        Owca owca = new Owca(9,9,swiat);
        swiat.nowyOrganizm(owca);
        Wilk wilk1 = new Wilk(2,2,swiat);
        swiat.nowyOrganizm(wilk1);
        Wilk wilk2 = new Wilk(3,2,swiat);
        swiat.nowyOrganizm(wilk2);
        Antylopa antylopa = new Antylopa(2,9,swiat);
        swiat.nowyOrganizm(antylopa);
        Barszcz zolw = new Barszcz(1,1,swiat);
        swiat.nowyOrganizm(zolw);
        Trawa trawa = new Trawa(5,5,swiat);
        swiat.nowyOrganizm(trawa);
        Guarana mlecz = new Guarana(6,6,swiat);
        swiat.nowyOrganizm(mlecz);
        Jagody jagody = new Jagody(8,8,swiat);
        swiat.nowyOrganizm(jagody);
        Czlowiek czlowiek = new Czlowiek(5,9,swiat);
        swiat.nowyOrganizm(czlowiek);
    }
}