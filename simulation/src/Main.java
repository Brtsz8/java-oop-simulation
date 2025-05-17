import UI.WorldPanel;
import classes.*;
import classes.animals.Owca;
import classes.animals.Wilk;
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

        Owca owca = new Owca(10,10,swiat);
        swiat.nowyOrganizm(owca);
        Wilk wilk1 = new Wilk(2,2,swiat);
        swiat.nowyOrganizm(wilk1);
        Wilk wilk2 = new Wilk(1,1,swiat);
        swiat.nowyOrganizm(wilk2);
        Wilk wilk3 = new Wilk(2,1,swiat);
        swiat.nowyOrganizm(wilk3);
        /*
        swiat.wykonajTure();
        swiat.wykonajTure();
        swiat.wykonajTure();
*/

    }
}