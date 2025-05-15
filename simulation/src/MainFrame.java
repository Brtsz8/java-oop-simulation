import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    //KONSTRUKTOR MAIN FRAME
    MainFrame(Settings settings) {
        JPanel worldPanel = new JPanel();
        worldPanel.setBackground(Color.green);
        worldPanel.setBounds(0,0,settings.getWidth(),settings.getHeight());

        JPanel logPanel = new JPanel();
        logPanel.setBackground(Color.red);
        logPanel.setBounds(250,0,250,250);

        JLabel label = new JLabel();
        label.setText("PO Simulation - Bartosz Pacyga 203833");

        this.setTitle("JAVA SIMULATION BARTOSZ PACYGA S203833");  //INFO
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(250,220,210));

        this.add(logPanel);
        this.add(worldPanel);
        this.add(label);

    }
}
