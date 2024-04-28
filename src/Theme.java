import java.util.ArrayList;

public class Theme {
    private String themeName;
    private ArrayList<CardsPacket> cardsPackets = new ArrayList<>();

    public String getThemeName() {
        return themeName;
    }
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
    public ArrayList<CardsPacket> getCardsPackets() {
        return cardsPackets;
    }
    public void setCardsPackets(ArrayList<CardsPacket> cardsPackets) {
        this.cardsPackets = cardsPackets;
    }
}
