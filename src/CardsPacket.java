import java.util.ArrayList;

public class CardsPacket {
    private int id;
    private String name;
    private ArrayList<Card> cards = new ArrayList<>();

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
