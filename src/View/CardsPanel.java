package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardsPanel extends JPanel {
    private ArrayList<JButton> cardButtons= new ArrayList<JButton>();
    public CardsPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel header = new JLabel("Карточки");
        header.setFont(new Font("Arial", Font.BOLD, 36));

        this.add(header);
    }
    public ArrayList<JButton> getCardButtons(){ return cardButtons; }

    public void setCards(String[] cardNames){
        cardButtons.clear();
        for(String cardName : cardNames){
            JButton button = new JButton(cardName);
            cardButtons.add(button);

            this.add(button);
        }
        Update();
    }

    private void Update(){
        this.revalidate();
        this.repaint();
    }
}
