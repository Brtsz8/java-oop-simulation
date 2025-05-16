package classes;
import UI.WorldPanel;
import mainFiles.Settings;

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

    public int getCommand() {
        return command;
    }

    public Settings getSettings() {
        return settings;
    }

    public WorldPanel getWorldPanel() {
        return worldPanel;
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
            System.out.println(logs.get(logIndex));
        }
    }

    public void sortujWszystkie() {
        organizmy.sort((a,b) -> Integer.compare(b.getInicjatywa(), a.getInicjatywa()));
    }

    public void wykonajTure() {
        sortujWszystkie();
        for (Organizm organizm : organizmy){
            if(organizm.getZyje()){
                //organizm.akcja();
            }
        }
        usunZabite();
        organizmy.addAll(nowe);
        nowe.clear();

        rysujSwiat();
        wyswietlLogi(topLog);
    }

    public void rysujSwiat() {
        System.out.println("Rysuje swiat");
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
    }
}
