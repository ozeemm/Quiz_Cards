package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final HeaderPanel headerPanel;
    private final GroupPanel themesPanel;
    private final GroupPanel packetsPanel;
    private final GroupPanel cardsPanel;
    private GroupPanel currentPanel = null;

    public MainFrame(){
        super("Quiz Cards Admin"); // Окно с названием
        setSize(750, 600); // Размер окна
        setResizable(false); // Запрещаем растяжение окна
        setLocationRelativeTo(null); // Чтобы окно открылось в центре экрана
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        setVisible(true); // Включаем окно

        headerPanel = new HeaderPanel();
        themesPanel = new GroupPanel();
        packetsPanel = new GroupPanel();
        cardsPanel = new GroupPanel();

        this.add(headerPanel, BorderLayout.NORTH);
    }
    public HeaderPanel getHeaderPanel(){ return headerPanel; }
    public GroupPanel getThemesPanel(){ return themesPanel; }
    public GroupPanel getPacketsPanel(){ return packetsPanel; }
    public GroupPanel getCardsPanel(){ return cardsPanel; }
    public GroupPanel getCurrentPanel(){ return currentPanel; }


    public void OpenPanel(GroupPanel panel){
        if(currentPanel != null)
            this.remove(currentPanel);
        this.add(panel);

        currentPanel = panel;
        update();

        if(currentPanel == themesPanel)
            headerPanel.setHeaderText("Темы");
        else if(currentPanel == packetsPanel)
            headerPanel.setHeaderText("Пакеты");
        else if(currentPanel == cardsPanel)
            headerPanel.setHeaderText("Карточки");
    }

    private void update(){
        this.revalidate();
        this.repaint();
    }
}
