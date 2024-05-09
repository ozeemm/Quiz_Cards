package View;

import javax.swing.*;

public class MainFrame extends JFrame {
    private ThemesPanel themesPanel;
    private PacketsPanel packetsPanel;
    private CardsPanel cardsPanel;
    private JPanel currentPanel = null;

    public ThemesPanel getThemesPanel(){ return themesPanel; }
    public PacketsPanel getPacketsPanel(){ return packetsPanel; }
    public CardsPanel getCardsPanel(){ return cardsPanel; }

    public MainFrame(){
        super("Quiz Cards Admin"); // Окно с названием
        setSize(950, 750); // Размер окна
        setResizable(false); // Запрещаем растяжение окна
        setLocationRelativeTo(null); // Чтобы окно открылось в центре экрана
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        setVisible(true); // Включаем окно
        //ImageIcon icon = new ImageIcon("./img/icon2.png");
        //this.setIconImage(icon.getImage()); // Иконка окна

        themesPanel = new ThemesPanel();
        packetsPanel = new PacketsPanel();
        cardsPanel = new CardsPanel();
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
