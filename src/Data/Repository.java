package Data;

import Model.*;

import java.util.ArrayList;

public class Repository {
    private ServerDataWorker serverDataWorker;
    private ServerDataManagementWorker serverDataManagementWorker;

    private ArrayList<Theme> themes;
    private ArrayList<CardsPacket> packets;
    private ArrayList<Card> cards;

    private Theme currentTheme;
    private CardsPacket currentPacket;

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
        if(currentTheme != themes.get(themeArrIndex)) {
            currentTheme = themes.get(themeArrIndex);
            packets = serverDataWorker.getPackets(currentTheme.getId());
        }
        return packets;
    }
    public ArrayList<CardsPacket> getPackets(Theme theme){
        packets = serverDataWorker.getPackets(theme.getId());
        return packets;
    }
    public ArrayList<Card> getCards(int packetArrIndex){
        if(currentPacket != packets.get(packetArrIndex)) {
            currentPacket = packets.get(packetArrIndex);
            cards = serverDataWorker.getCards(currentPacket.getId());
        }
        return cards;
    }
    public ArrayList<Card> getCards(CardsPacket packet){
        cards = serverDataWorker.getCards(packet.getId());
        return cards;
    }

    public String[] getThemeNames(){
        return getThemes().stream().map(t -> t.getName()).toArray(String[]::new);
    }
    public String[] getPacketNames(int themeArrIndex){
        return getPackets(themeArrIndex).stream().map(p -> p.getName()).toArray(String[]::new);
    }
    public String[] getPacketNames(){
        return getPackets(currentTheme).stream().map(p -> p.getName()).toArray(String[]::new);
    }
    public String[] getCardNames(int packetArrInd){
        return getCards(packetArrInd).stream().map(c -> c.getFrontText() + " : " + c.getBackText()).toArray(String[]::new);
    }
    public String[] getCardNames(){
        return getCards(currentPacket).stream().map(c -> c.getFrontText() + " : " + c.getBackText()).toArray(String[]::new);
    }

    public Theme getTheme(int index){ return themes.get(index); }
    public CardsPacket getPacket(int index){ return packets.get(index); }
    public Card getCard(int index){ return cards.get(index); }

    public void createTheme(String name, String description){
        Theme theme = new Theme();
        theme.setName(name);
        theme.setDescription(description);
        serverDataManagementWorker.createTheme(theme);
        themes = null;
    }
    public void createPacket(String name, String description){
        CardsPacket packet = new CardsPacket();
        packet.setName(name);
        packet.setDescription(description);
        packet.setThemeId(currentTheme.getId());
        serverDataManagementWorker.createPacket(currentTheme, packet);
    }
    public void createCard(String frontText, String backText){
        Card card = new Card();
        card.setFrontText(frontText);
        card.setBackText(backText);
        card.setPacketId(currentPacket.getId());
        serverDataManagementWorker.createCard(currentPacket, card);
    }

    public void updateTheme(int arrIndex, String name, String description){
        Theme theme = themes.get(arrIndex);
        theme.setName(name);
        theme.setDescription(description);
        serverDataManagementWorker.updateTheme(theme);
        themes = null;
    }
    public void updatePacket(int arrIndex, String name, String description){
        CardsPacket packet = packets.get(arrIndex);
        packet.setName(name);
        packet.setDescription(description);
        serverDataManagementWorker.updatePacket(packet);
    }
    public void updateCard(int arrIndex, String frontText, String backText){
        Card card = cards.get(arrIndex);
        card.setFrontText(frontText);
        card.setBackText(backText);
        serverDataManagementWorker.updateCard(card);
    }

    public void deleteTheme(int arrIndex){
        Theme theme = themes.get(arrIndex);
        serverDataManagementWorker.deleteTheme(theme);
        themes = null;
    }
    public void deletePacket(int arrIndex){
        CardsPacket packet = packets.get(arrIndex);
        serverDataManagementWorker.deletePacket(packet);
    }
    public void deleteCard(int arrIndex){
        Card card = cards.get(arrIndex);
        serverDataManagementWorker.deleteCard(card);
    }
}
