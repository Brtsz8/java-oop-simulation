import UI.WorldPanel;
import classes.*;
import classes.animals.Wilk;
import mainFiles.MainFrame;
import mainFiles.Settings;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Settings worldSettings = new Settings("src/files/settings.txt");
        MainFrame mainFrame = new MainFrame(worldSettings);
        WorldPanel mainWorld = mainFrame.getWorldPanel();

        Swiat swiat = new Swiat(mainWorld, worldSettings);
        mainWorld.setSwiat(swiat);

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