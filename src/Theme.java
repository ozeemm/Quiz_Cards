import java.util.ArrayList;

public class Theme {
    private int id;
    private String name;
    private ArrayList<CardsPacket> cardsPackets = new ArrayList<>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<CardsPacket> getCardsPackets() {
        return cardsPackets;
    }
    public void setCardsPackets(ArrayList<CardsPacket> cardsPackets) {
        this.cardsPackets = cardsPackets;
    }
}
