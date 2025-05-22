import UI.WorldPanel;
import classes.*;
import classes.animals.*;
import classes.plants.*;
import mainFiles.MainFrame;
import mainFiles.Settings;

import javax.swing.*;
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

        // Popup for choosing test case
        String[] options = {"Test Case 1", "Test Case 2", "Empty World"};
        String choice = (String) JOptionPane.showInputDialog(
                null,
                "Wybierz poziom testowy",
                "Wczytywanie świata",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == null ) {
            System.out.println("Brak wyboru. Zamykam aplikację.");
            System.exit(0);
        }

        // load world state based on selection
        switch (choice) {
            case "Test Case 1" -> {
                swiat.nowyOrganizm(new Owca(9,9,swiat));
                swiat.nowyOrganizm(new Wilk(2,2,swiat));
                swiat.nowyOrganizm(new Wilk(3,2,swiat));
                swiat.nowyOrganizm(new Antylopa(2,9,swiat));
                swiat.nowyOrganizm(new Lis(1,1,swiat));
                swiat.nowyOrganizm(new Trawa(5,5,swiat));
                swiat.nowyOrganizm(new Guarana(6,6,swiat));
                swiat.nowyOrganizm(new Jagody(8,8,swiat));
                swiat.nowyOrganizm(new Czlowiek(5,9,swiat));
            }
            case "Test Case 2" -> {
                swiat.nowyOrganizm(new Lis(9,9,swiat));
                swiat.nowyOrganizm(new Trawa(5,5,swiat));
                swiat.nowyOrganizm(new Guarana(3,3,swiat));
                swiat.nowyOrganizm(new Jagody(8,8,swiat));
                swiat.nowyOrganizm(new Czlowiek(1,1,swiat));
            }
            case "Empty World" -> {
                swiat.nowyOrganizm(new Czlowiek(5,5,swiat));
            }
        }


    }
}