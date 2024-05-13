package View;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class GroupElement extends JPanel {
    private JButton elementButton;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel elementsCountLabel;
    private int elementId;

    private final Color borderColor = Color.white;
    private final Color backgroundColor = Color.lightGray;
    private final Color buttonColor = Color.gray;
    private final Color buttonTextColor = Color.white;
    private final Font buttonFont = new Font("Arial", Font.PLAIN, 16);
    private final Font labelFont = new Font("Arial", Font.ITALIC, 14);

    public GroupElement(){
        super(new MigLayout("insets 10"));
        this.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        this.setBackground(backgroundColor);

        elementButton = new JButton();
        elementButton.setBackground(buttonColor);
        elementButton.setForeground(buttonTextColor);
        elementButton.setFont(buttonFont);

        editButton = new JButton("Изменить");
        editButton.setBackground(buttonColor);
        editButton.setForeground(buttonTextColor);
        editButton.setFont(buttonFont);

        deleteButton = new JButton("Удалить");
        deleteButton.setBackground(buttonColor);
        deleteButton.setForeground(buttonTextColor);
        deleteButton.setFont(buttonFont);

        elementsCountLabel = new JLabel();
        elementsCountLabel.setFont(labelFont);
        elementsCountLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(elementButton, "span, grow");
        this.add(editButton);
        this.add(deleteButton, "wrap");
        this.add(elementsCountLabel, "span, grow");
    }

    public JButton getElementButton(){ return elementButton; }
    public JButton getEditButton(){ return editButton; }
    public JButton getDeleteButton(){ return deleteButton; }

    public int getElementId() {
        return elementId;
    }
    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public void setElementsCountText(String text){
        elementsCountLabel.setText(text);
        update();
    }
    public void setElementName(String text){
        elementButton.setText(text);
        update();
    }
    private void update(){
        this.revalidate();
        this.repaint();
    }
}
