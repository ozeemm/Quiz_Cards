package View;

import javax.swing.*;

public class EditFrame extends JFrame {
    private JPanel mainPanel;
    private JLabel header;
    private JLabel paramLabel1;
    private JLabel paramLabel2;
    private JTextField paramInput1;
    private JTextField paramInput2;
    private JButton saveButton;
    private JButton backButton;

    protected JPanel getMainPanel(){ return mainPanel; }

    public EditFrame(){
        super("Edit"); // Окно с названием
        setSize(316, 375); // Размер окна
        setResizable(false); // Запрещаем растяжение окна
        setLocationRelativeTo(null); // Чтобы окно открылось в центре экрана
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        setVisible(true); // Включаем окно

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        header = new JLabel("Изменение");

        paramLabel1 = new JLabel("param1");

        paramInput1 = new JTextField();

        paramLabel2 = new JLabel("param2");

        paramInput2 = new JTextField();

        saveButton = new JButton("Сохранить");
        backButton = new JButton("Назад");

        mainPanel.add(header);
        mainPanel.add(paramLabel1);
        mainPanel.add(paramInput1);
        mainPanel.add(paramLabel2);
        mainPanel.add(paramInput2);
        mainPanel.add(saveButton);
        mainPanel.add(backButton);
        this.add(mainPanel);
    }

    public void Update(){
        this.revalidate();
        this.repaint();
    }
}
