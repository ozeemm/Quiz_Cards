package View;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private JLabel header;
    private JButton backButton;
    private JButton addButton;
    private final Color backgroundColor = Color.lightGray;
    private final Color buttonColor = Color.gray;
    private final Color buttonTextColor = Color.white;
    private final Font headerFont = new Font("Arial", Font.BOLD, 24);
    private final Font buttonFont = new Font("Arial", Font.PLAIN, 14);

    public HeaderPanel() {
        super(new MigLayout("", "[]push[]push[]"));
        this.setBackground(backgroundColor);

        header = new JLabel();
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setFont(headerFont);

        backButton = new JButton("Назад");
        backButton.setBackground(buttonColor);
        backButton.setForeground(buttonTextColor);
        backButton.setFont(buttonFont);

        addButton = new JButton("Создать");
        addButton.setBackground(buttonColor);
        addButton.setForeground(buttonTextColor);
        addButton.setFont(buttonFont);

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
