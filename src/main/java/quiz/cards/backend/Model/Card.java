package quiz.cards.backend.Model;

public class Card {
    private int id;
    private String frontText;
    private String backText;
    private int packetId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFrontText() {
        return frontText;
    }
    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }
    public String getBackText() {
        return backText;
    }
    public void setBackText(String backText) {
        this.backText = backText;
    }
    public int getPacketId() {
        return packetId;
    }
    public void setPacketId(int packetId) {
        this.packetId = packetId;
    }
}
