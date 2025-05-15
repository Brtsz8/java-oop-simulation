import javax.swing.JFrame;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Settings worldSettings = new Settings("src/files/settings.txt");
        MainFrame mainFrame = new MainFrame(worldSettings);
    }
}