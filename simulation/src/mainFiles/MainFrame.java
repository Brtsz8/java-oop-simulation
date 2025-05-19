package mainFiles;

import UI.WorldPanel;
import classes.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {
    private WorldPanel worldPanel;
    protected Swiat swiat;
    //KONSTRUKTOR MAIN FRAME
    public MainFrame(Settings settings) {
        this.addKeyListener(this);
        worldPanel = new WorldPanel(settings.getTileSize());
        worldPanel.setBackground(Color.green);
        worldPanel.setBounds(10,10,settings.getWidth(),settings.getHeight());

        JPanel logPanel = new JPanel();
        logPanel.setBackground(Color.red);
        logPanel.setBounds(settings.getWidth()+20,10,settings.getWidth(),settings.getHeight());

        JLabel label = new JLabel();
        label.setText("PO Simulation - Bartosz Pacyga 203833");

        JLabel textLogi = new JLabel();
        textLogi.setText("Zmiany w swiecie: \uD83C\uDF0E");
        System.out.println("\uD83C\uDF0E");
        this.setTitle("JAVA SIMULATION BARTOSZ PACYGA S203833");  //INFO
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        int w = settings.getWidth()*2+100;
        int h = settings.getHeight()+100;
        try{
            this.setSize(w,h);
            this.setBounds(0,0,w,h);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        this.setVisible(true);
        this.getContentPane().setBackground(new Color(250,220,210));

        logPanel.add(textLogi);
        this.add(logPanel);
        this.add(worldPanel);
        this.add(label);
    }

    public WorldPanel getWorldPanel() { return worldPanel; }

    public void setSwiat(Swiat swiat){
        this.swiat = swiat;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        swiat.setCommand(e.getKeyChar());
        worldPanel.redraw();
        worldPanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
