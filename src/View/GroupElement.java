package View;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class GroupElement extends JPanel {
    private JButton elementButton;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel elementsCountLabel;
    private int elementId;

    public GroupElement(){
        super(new MigLayout("insets 10"));
        this.setBorder(BorderFactory.createLineBorder(Style.getBorderColor(), 2));
        this.setBackground(Style.getElementBackgroundColor());

        elementButton = new JButton();
        elementButton.setBackground(Style.getButtonBackgroundColor());
        elementButton.setForeground(Style.getButtonTextColor());
        elementButton.setFont(Style.getButtonFont());

        editButton = new JButton("Изменить");
        editButton.setBackground(Style.getButtonBackgroundColor());
        editButton.setForeground(Style.getButtonTextColor());
        editButton.setFont(Style.getButtonFont());

        deleteButton = new JButton("Удалить");
        deleteButton.setBackground(Style.getButtonBackgroundColor());
        deleteButton.setForeground(Style.getButtonTextColor());
        deleteButton.setFont(Style.getButtonFont());

        elementsCountLabel = new JLabel();
        elementsCountLabel.setFont(Style.getElementInfoFont());
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
