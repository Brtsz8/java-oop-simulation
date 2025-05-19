package classes;
import UI.WorldPanel;
import classes.animals.*;
import classes.plants.*;
import mainFiles.Settings;

import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Swiat {
    private List<Organizm> organizmy = new ArrayList<>();
    private List<Organizm> nowe = new ArrayList<>();
    private List<String> logs = new ArrayList<>();
    private WorldPanel worldPanel;
    private Settings settings;

    private int topLog = 0;
    private int command = 0;

    //konstruktor
    public Swiat(WorldPanel worldPanel, Settings settings) {
        this.worldPanel = worldPanel;
        this.settings = settings;
    }

    public void usunOrganizmy() {
        for (Organizm organizm : organizmy) {
            //podobno w javie sie nie usuwa
            //do zrobienia potem
        }
        organizmy.clear();
    }

    public void cleanUp() {
        usunOrganizmy();
        nowe.clear();
        logs.clear();
    }

    public int getTopLogIndex() {
        return topLog;
    }

    public List<Organizm> getOrganizmy(){
        return organizmy;
    }

    public int getCommand() {
        return command;
    }

    public Settings getSettings() {
        return settings;
    }

    public WorldPanel getWorldPanel() {
        return worldPanel;
    }

    public List<String> getLogs() {
        return logs;
    }

    //tutaj z jakiegos powodu zalozylem w poprzednim projekcie ze 10 linijek ma byc
    //do zrobienia
    public void setTopLogIndex(int index) {
        if (index >= 0 && index <= logs.size() - 10) {
            topLog = index;
        }
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public void nowyOrganizm(Organizm organizm) {
        nowe.add(organizm);
    }

    public void nowyLog(String log) {
        logs.add(log);
    }

    //tymczasowe
    public void wyswietlLogi(int pierwszyLog) {
        int maxLines = 10;  //znow nie wiem czemo ???
        for(int i = 0; i < maxLines && i < logs.size(); i++) {
            int logIndex = pierwszyLog + i;
            if (logIndex >= logs.size()) break;
            if (logIndex < 0) logIndex = 0;
            //System.out.println(logs.get(logIndex));
        }
    }

    public void sortujWszystkie() {
        organizmy.sort((a,b) -> Integer.compare(b.getInicjatywa(), a.getInicjatywa()));
    }

    public void wykonajTure() {
        sortujWszystkie();
        for (Organizm organizm : organizmy){
            if(organizm.getZyje()){
                organizm.akcja();
            }
        }
        usunZabite();
        organizmy.addAll(nowe);
        nowe.clear();

       // rysujSwiat();
        wyswietlLogi(topLog);
    }

    public void rysujSwiat() {
       // worldPanel.redraw();
    }

    public Organizm findOrganismAt(int x, int y) {
        for(Organizm organizm : organizmy) {
            if(organizm.getPozycjaX() == x && organizm.getPozycjaY() == y) {
                return organizm;
            }
        }
        for(Organizm organizm : nowe) {
            if(organizm.getPozycjaX() == x && organizm.getPozycjaY() == y) {
                return organizm;
            }
        }
        return null;
    }

    public void usunZabite() {
        Iterator<Organizm> iterator = organizmy.iterator();
        while(iterator.hasNext()) {
            Organizm organizm = iterator.next();
            if(!organizm.getZyje()) {
                iterator.remove();
            }
        }
        Iterator<Organizm> iterator2 = nowe.iterator();
        while(iterator2.hasNext()) {
            Organizm organizm = iterator2.next();
            if(!organizm.getZyje()) {
                iterator2.remove();
            }
        }
    }

    public void save(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Win");

            for (Organizm organizm : organizmy) {
                writer.print(organizm.nazwa() + " ");
                writer.print(organizm.getPozycjaX() + " ");
                writer.print(organizm.getPozycjaY() + " ");
                writer.print(organizm.getSila());

                if (organizm instanceof Czlowiek cz) {
                    writer.print(" ");
                    writer.print(cz.isUmiejetnoscAktywna() ? 1 : 0);
                    writer.print(" ");
                    writer.print(cz.getDlugoscUmiejetnosci());
                    writer.print(" ");
                    writer.print(cz.getDlugoscRegeneracji());
                }

                writer.println();
            }

            writer.println("LogWindow");
            for (String log : logs) {
                writer.println(log);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(String filePath) {
        organizmy.clear();
        logs.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // "Win"
            while ((line = reader.readLine()) != null && !line.equals("LogWindow")) {
                String[] parts = line.split(" ");
                String nazwa = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                int sila = Integer.parseInt(parts[3]);

                Organizm organizm = null;

                switch (nazwa) {
                    case "Wilk" -> organizm = new Wilk(x, y, this);
                    case "Owca" -> organizm = new Owca(x, y, this);
                    case "Lis" -> organizm = new Lis(x, y, this);
                    case "Zolw" -> organizm = new Zolw(x, y, this);
                    case "Antylopa" -> organizm = new Antylopa(x, y, this);
                    case "Trawa" -> organizm = new Trawa(x, y, this);
                    case "Mlecz" -> organizm = new Mlecz(x, y, this);
                    case "Guarana" -> organizm = new Guarana(x, y, this);
                    case "Wilcze Jagody" -> organizm = new Jagody(x, y, this);
                    case "Barszcz Sosnowskiego" -> organizm = new Barszcz(x, y, this);
                    case "Czlowiek" -> {
                        boolean umiejetnoscAktywna = Integer.parseInt(parts[4]) == 1;
                        int dlugoscUmiejetnosci = Integer.parseInt(parts[5]);
                        int dlugoscRegeneracji = Integer.parseInt(parts[6]);
                        Czlowiek cz = new Czlowiek(x, y, this);
                        cz.setSila(sila);
                        cz.setUmiejetnoscAktywna(umiejetnoscAktywna);
                        cz.setDlugoscUmiejetnosci(dlugoscUmiejetnosci);
                        cz.setDlugoscRegeneracji(dlugoscRegeneracji);
                        organizm = cz;
                    }
                }

                if (organizm != null && !(organizm instanceof Czlowiek)) {
                    organizm.setSila(sila);
                }

                if (organizm != null) {
                    nowyOrganizm(organizm);
                }
            }

            // Wczytaj logi
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    logs.add(line);
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
