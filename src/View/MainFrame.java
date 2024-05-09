package View;

import javax.swing.*;

public class MainFrame extends JFrame {
    private GroupPanel themesPanel;
    private GroupPanel packetsPanel;
    private GroupPanel cardsPanel;
    private JPanel currentPanel = null;

    public GroupPanel getThemesPanel(){ return themesPanel; }
    public GroupPanel getPacketsPanel(){ return packetsPanel; }
    public GroupPanel getCardsPanel(){ return cardsPanel; }
    public MainFrame(){
        super("Quiz Cards Admin"); // Окно с названием
        setSize(950, 750); // Размер окна
        setResizable(false); // Запрещаем растяжение окна
        setLocationRelativeTo(null); // Чтобы окно открылось в центре экрана
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        setVisible(true); // Включаем окно
        //ImageIcon icon = new ImageIcon("./img/icon2.png");
        //this.setIconImage(icon.getImage()); // Иконка окна

        themesPanel = new GroupPanel();
        themesPanel.setHeaderText("Темы");

        packetsPanel = new GroupPanel();
        packetsPanel.setHeaderText("Пакеты");

        cardsPanel = new GroupPanel();
        cardsPanel.setHeaderText("Карточки");
    }

    public void OpenPanel(JPanel panel){
        if(currentPanel != null)
            this.remove(currentPanel);
        this.add(panel);

        currentPanel = panel;
        Update();
    }

    private void Update(){
        this.revalidate();
        this.repaint();
    }
}
