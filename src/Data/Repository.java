package Data;

import Model.*;

import java.util.ArrayList;

public class Repository {
    private ServerDataWorker serverDataWorker;
    private ServerDataManagementWorker serverDataManagementWorker;

    private ArrayList<Theme> themes;
    private ArrayList<CardsPacket> packets;
    private ArrayList<Card> cards;

    private int currentTheme = -1; // id из локального списка
    private int currentPacket = -1; // id из локального списка

    public Repository(){
        serverDataWorker = new ServerDataWorker();
        serverDataManagementWorker = new ServerDataManagementWorker();
    }

    public ArrayList<Theme> getThemes(){
        if(themes == null)
            themes = serverDataWorker.getThemes();
        return themes;
    }
    public ArrayList<CardsPacket> getPackets(int themeArrIndex){
        if(currentTheme != themeArrIndex) {
            currentTheme = themeArrIndex;
            int themeId = themes.get(themeArrIndex).getId();
            packets = serverDataWorker.getPackets(themeId);
        }
        return packets;
    }
    public ArrayList<Card> getCards(int packetArrIndex){
        if(currentPacket != packetArrIndex) {
            currentPacket = packetArrIndex;
            int packetId = packets.get(packetArrIndex).getId();
            cards = serverDataWorker.getCards(packetId);
        }
        return cards;
    }

    public String[] getThemeNames(){
        return getThemes().stream().map(t -> t.getName()).toArray(String[]::new);
    }
    public String[] getPacketNames(int themeArrIndex){
        return getPackets(themeArrIndex).stream().map(p -> p.getName()).toArray(String[]::new);
    }
    public String[] getCardNames(int packetArrInd){
        return getCards(packetArrInd).stream().map(c -> c.getFrontText() + " : " + c.getBackText()).toArray(String[]::new);
    }

    public Theme getTheme(int index){ return themes.get(index); }
    public CardsPacket getPacket(int index){ return packets.get(index); }
    public Card getCard(int index){ return cards.get(index); }
}
