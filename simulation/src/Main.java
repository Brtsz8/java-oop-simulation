import UI.WorldPanel;
import classes.Swiat;
import mainFiles.MainFrame;
import mainFiles.Settings;

public class Main {
    public static void main(String[] args) {
        Settings worldSettings = new Settings("src/files/settings.txt");
        MainFrame mainFrame = new MainFrame(worldSettings);
        WorldPanel mainWorld = mainFrame.getWorldPanel();

        Swiat swiat = new Swiat(mainWorld, worldSettings);

    }
}