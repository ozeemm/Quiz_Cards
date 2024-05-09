package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GroupPanel extends JPanel {
    private final ArrayList<JButton> elementButtons = new ArrayList<JButton>();
    private final ArrayList<JButton> editButtons = new ArrayList<JButton>();
    private final ArrayList<JButton> deleteButtons = new ArrayList<JButton>();
    private JLabel header;
    private JButton addButton;
    private JButton backButton;
    public GroupPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        header = new JLabel();
        addButton = new JButton("Создать");
        backButton = new JButton("Назад");

        this.add(header);
        this.add(addButton);
        this.add(backButton);
    }

    public ArrayList<JButton> getElementButtons() {
        return elementButtons;
    }
    public ArrayList<JButton> getEditButtons() {
        return editButtons;
    }
    public ArrayList<JButton> getDeleteButtons() {
        return deleteButtons;
    }
    public JButton getAddButton(){ return addButton; }
    public JButton getBackButton(){ return backButton; }

    public void setHeaderText(String text){
        header.setText(text);
        header.setFont(new Font("Arial", Font.BOLD, 36));
        update();
    }
    public void setElements(String[] names){
        clearAllButtons();

        for(String name : names){
            JButton button = new JButton(name);
            elementButtons.add(button);
            this.add(button);

            button = new JButton("Изменить");
            editButtons.add(button);
            this.add(button);

            button = new JButton("Удалить");
            deleteButtons.add(button);
            this.add(button);
        }

        update();
    }

    private void update(){
        this.revalidate();
        this.repaint();
    }

    private void clearAllButtons(){
        clearButtons(elementButtons);
        clearButtons(editButtons);
        clearButtons(deleteButtons);
    }
    private void clearButtons(ArrayList<JButton> buttons){
        for(JButton button : buttons){
            this.remove(button);
        }
        buttons.clear();
    }
}
