package View;

import Model.Style;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private JLabel header;
    private JButton backButton;
    private JButton addButton;

    public HeaderPanel() {
        super(new MigLayout("", "[]push[]push[]"));
        this.setBackground(Style.getPanelBackgroundColor());

        header = new JLabel();
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setFont(Style.getHeaderFont());

        backButton = new JButton("Назад");
        backButton.setBackground(Style.getButtonBackgroundColor());
        backButton.setForeground(Style.getButtonTextColor());
        backButton.setFont(Style.getButtonFont());

        addButton = new JButton("Создать");
        addButton.setBackground(Style.getButtonBackgroundColor());
        addButton.setForeground(Style.getButtonTextColor());
        addButton.setFont(Style.getButtonFont());

        this.add(backButton);
        this.add(header);
        this.add(addButton);
    }

    public JButton getAddButton(){ return addButton; }
    public JButton getBackButton(){ return backButton; }

    public void setHeaderText(String text){
        header.setText(text);
        update();
    }

    private void update(){
        this.revalidate();
        this.repaint();
    }
}
