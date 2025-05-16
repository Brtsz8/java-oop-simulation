import UI.worldPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    //KONSTRUKTOR MAIN FRAME
    MainFrame(Settings settings) {
        JPanel worldPanel = new worldPanel(settings.getTileSize());
        worldPanel.setBackground(Color.green);
        worldPanel.setBounds(10,10,settings.getWidth(),settings.getHeight());

        JPanel logPanel = new JPanel();
        logPanel.setBackground(Color.red);
        logPanel.setBounds(settings.getWidth()+20,10,settings.getWidth(),settings.getHeight());

        JLabel label = new JLabel();
        label.setText("PO Simulation - Bartosz Pacyga 203833");

        this.setTitle("JAVA SIMULATION BARTOSZ PACYGA S203833");  //INFO
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        int w = settings.getWidth()*2+100;
        int h = settings.getHeight()+100;
       // System.out.println(w);
       // System.out.println(h);
        try{
            this.setSize(w,h);
            this.setBounds(0,0,w,h);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        this.setVisible(true);
        this.getContentPane().setBackground(new Color(250,220,210));

        this.add(logPanel);
        this.add(worldPanel);
        this.add(label);

    }
}
