package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ThemesPanel extends JPanel {
    private final ArrayList<JButton> themeButtons = new ArrayList<JButton>();
    public ThemesPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel header = new JLabel("Темы");
        header.setFont(new Font("Arial", Font.BOLD, 36));

        this.add(header);
    }
    public ArrayList<JButton> getThemeButtons(){ return themeButtons; }

    public void setThemes(String[] themeNames){
        themeButtons.clear();
        for(String themeName : themeNames){
            JButton button = new JButton(themeName);
            themeButtons.add(button);

            this.add(button);
            this.add(new JButton("Изменить"));
            this.add(new JButton("Удалить"));
        }
        Update();
    }

    private void Update(){
        this.revalidate();
        this.repaint();
    }
}
