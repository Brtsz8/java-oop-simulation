import UI.WorldPanel;
import classes.*;
import classes.animals.Antylopa;
import classes.animals.Owca;
import classes.animals.Wilk;
import classes.animals.Zolw;
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
        mainWorld.setSwiat(swiat);

        Owca owca = new Owca(9,9,swiat);
        swiat.nowyOrganizm(owca);
        Wilk wilk1 = new Wilk(2,2,swiat);
        swiat.nowyOrganizm(wilk1);
        Antylopa antylopa = new Antylopa(2,9,swiat);
        swiat.nowyOrganizm(antylopa);
        Zolw zolw = new Zolw(1,1,swiat);
        swiat.nowyOrganizm(zolw);

    }
}