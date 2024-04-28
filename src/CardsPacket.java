import java.util.ArrayList;

public class CardsPacket {
    private String packetName;
    private ArrayList<Card> cards = new ArrayList<>();

    public String getPacketName() {
        return packetName;
    }
    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

}
