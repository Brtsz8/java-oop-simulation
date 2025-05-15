import javax.swing.JFrame;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("JAVA SIMULATION BARTOSZ PACYGA S203833");  //INFO
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(0x123456));
    }
}