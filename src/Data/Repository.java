package Data;

import Model.*;

import java.util.ArrayList;

public class Repository {
    private ServerDataWorker serverDataWorker;
    private ServerDataManagementWorker serverDataManagementWorker;

    private ArrayList<Theme> themes;
    private ArrayList<CardsPacket> packets;
    private ArrayList<Card> cards;

    private int currentTheme = -1; // id из БД
    private int currentPacket = -1; // id из БД

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
        int themeId = themes.get(themeArrIndex).getId();

        if(currentTheme != themeId) {
            packets = serverDataWorker.getPackets(themeId);
            currentTheme = themeId;
        }

        return packets;
    }

    public ArrayList<Card> getCards(int packetArrIndex){
        int packetId = packets.get(packetArrIndex).getId();

        if(currentPacket != packetId) {
            cards = serverDataWorker.getCards(packetId);
            currentPacket = packetId;
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
}
