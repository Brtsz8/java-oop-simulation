package mainFiles;

import UI.LogPanel;
import UI.WorldPanel;
import classes.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {
    private WorldPanel worldPanel;
    private LogPanel logPanel;
    protected Swiat swiat;
    //KONSTRUKTOR MAIN FRAME
    // KONSTRUKTOR MAIN FRAME
    public MainFrame(Settings settings) {
        this.addKeyListener(this);
        worldPanel = new WorldPanel(settings.getTileSize());
        worldPanel.setBackground(new Color(146, 130, 141));
        worldPanel.setBounds(10, 10, settings.getWidth(), settings.getHeight());

        logPanel = new LogPanel(settings, swiat);

        JLabel label = new JLabel();

        JLabel textLogi = new JLabel();
        this.setTitle("JAVA SIMULATION BARTOSZ PACYGA S203833");  //INFO
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        int w = settings.getWidth() * 2 + 100;
        int h = settings.getHeight() + 140; // zwiększono wysokość, by zmieścić etykietę na dole
        try {
            this.setSize(w, h);
            this.setBounds(0, 0, w, h);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        this.setLayout(null); // ustawienie layoutu na null dla ręcznego pozycjonowania
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(146, 130, 141));

        logPanel.add(textLogi);
        this.add(logPanel);
        this.add(worldPanel);
        this.add(label);

        // Dodanie etykiety z informacjami o sterowaniu
        JLabel instructionLabel = new JLabel("Sterowanie: 3 - aktywuj zdolność, WASD - ruch Człowieka, Z - zapisz, X - wczytaj, 1/2 - przewijanie logów, dotknij planszy aby dodać organizm");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        instructionLabel.setBounds(10, h - 80, w - 20, 30); // pozycja przy dolnej krawędzi
        this.add(instructionLabel);
    }


    public WorldPanel getWorldPanel() { return worldPanel; }

    public void setSwiat(Swiat swiat){
        this.swiat = swiat;
        logPanel.setSwiat(swiat);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == '1') {
            swiat.setTopLogIndex(swiat.getTopLogIndex()+1);
            logPanel.updateLogs(swiat.getTopLogIndex());
            logPanel.repaint();
            return;
        }
        if(e.getKeyChar() == '2') {
            swiat.setTopLogIndex(swiat.getTopLogIndex()-1);
            logPanel.updateLogs(swiat.getTopLogIndex());
            logPanel.repaint();
            return;
        }
        if(e.getKeyChar() == 'z') {
            swiat.save("src/files/save.txt");
            worldPanel.repaint();
            logPanel.repaint();
            return;
        }
        if(e.getKeyChar() == 'x') {
            swiat.load("src/files/save.txt");
            worldPanel.repaint();
            logPanel.repaint();
            return;
        }
        swiat.setCommand(e.getKeyChar());
        worldPanel.redraw();
        worldPanel.repaint();
        logPanel.updateLogs(swiat.getTopLogIndex());
        logPanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
