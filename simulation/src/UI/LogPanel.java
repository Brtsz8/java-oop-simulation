package UI;

import classes.Swiat;
import mainFiles.Settings;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel {

    private Settings settings;
    private Swiat swiat;
    private JTextArea logArea;
    private String logMsgs = "";
    private int startLine = 0; // Control which line to start displaying

    private static final Color LOG_BACK = new Color(45, 48, 71); // light green
    private static final Color TEXT_WHITE = new Color(255, 253, 130);      // dark green

    public LogPanel(Settings settings, Swiat swiat){
        this.settings = settings;
        this.swiat = swiat;

        setLayout(new FlowLayout());
        this.setBackground(LOG_BACK);
        this.setBounds(settings.getWidth()+20,10,settings.getWidth(),settings.getHeight());

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBackground(LOG_BACK);
        logArea.setForeground(TEXT_WHITE);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 24));
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);

    }

    // Call this whenever you want to update the log display
    public void updateLogs(int startLine) {
        StringBuilder sb = new StringBuilder();
        for (String line : swiat.getLogs()) {
            sb.append(line).append("\n");
        }
        logArea.setForeground(TEXT_WHITE);
        logArea.setText(sb.toString());
        logMsgs = sb.toString();
        logArea.setCaretPosition(logArea.getDocument().getLength()); // Scroll to bottom
        this.startLine = startLine;
        logArea.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Monospaced", Font.PLAIN, 14));

        String[] lines = logMsgs.split("\n");
        int x = 10;
        int y = 20;

        for (int i = startLine; i < lines.length; i++) {
            g.drawString(lines[i], x, y);
            y += g.getFontMetrics().getHeight();
        }
    }

    public void setSwiat(Swiat swiat){
        this.swiat = swiat;
    }
}
