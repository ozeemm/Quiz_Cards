package View;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class EditFrame extends JFrame {
    private JPanel mainPanel;
    private JLabel header;
    private JLabel paramLabel1;
    private JLabel paramLabel2;
    private JTextField paramInput1;
    private JTextArea paramInput2;
    private JScrollPane scrollPane; // Скрол для второго параметра
    private JButton saveButton;
    private JButton backButton;
    private int editingElementId = -1;
    private Runnable createTitlesFunction;
    private Runnable editTitlesFunction;

    private final Color backgroundColor = new Color(238, 238, 238);
    private final Color inputBackgroundColor = Color.WHITE;
    private final Color textColor = Color.BLACK;
    private final Color buttonColor = Color.LIGHT_GRAY;
    private final Color buttonTextColor = Color.BLACK;
    private final Font headerFont = new Font("Arial", Font.BOLD, 18);
    private final Font textFont = new Font("Arial", Font.PLAIN, 14);
    private final Font inputFont = new Font("Arial", Font.PLAIN, 13);

    public EditFrame(){
        super();
        setSize(300, 255);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(false);

        mainPanel = new JPanel(new MigLayout("insets 10"));
        mainPanel.setBackground(backgroundColor);

        header = new JLabel("Изменение");
        header.setFont(headerFont);
        header.setForeground(textColor);
        header.setHorizontalAlignment(JLabel.CENTER);;

        paramLabel1 = new JLabel();
        paramLabel1.setFont(textFont);
        paramLabel1.setForeground(textColor);
        mainPanel.add(paramLabel1);

        paramInput1 = new JTextField(30);
        paramInput1.setBackground(inputBackgroundColor);
        paramInput1.setFont(inputFont);
        paramInput1.setForeground(textColor);

        paramLabel2 = new JLabel();
        paramLabel2.setFont(textFont);
        paramLabel2.setForeground(textColor);

        paramInput2 = new JTextArea(4, 0);
        paramInput2.setBackground(inputBackgroundColor);
        paramInput2.setFont(inputFont);
        paramInput2.setForeground(textColor);
        paramInput2.setLineWrap(true);
        paramInput2.setWrapStyleWord(true);

        scrollPane = new JScrollPane(paramInput2);

        saveButton = new JButton("Сохранить");
        saveButton.setFont(textFont);
        saveButton.setBackground(buttonColor);
        saveButton.setForeground(buttonTextColor);
        saveButton.addActionListener(e -> { this.setVisible(false); });

        backButton = new JButton("Назад");
        backButton.setFont(textFont);
        backButton.setBackground(buttonColor);
        backButton.setForeground(buttonTextColor);
        backButton.addActionListener(e -> { this.setVisible(false); });

        mainPanel.add(header, "span, grow, gapy 0 5");
        mainPanel.add(paramLabel1, "wrap");
        mainPanel.add(paramInput1, "span, grow");
        mainPanel.add(paramLabel2, "wrap");
        mainPanel.add(scrollPane, "span, grow, gapy 0 5");
        mainPanel.add(backButton);
        mainPanel.add(saveButton, "gapleft push");
        this.add(mainPanel);

        update();
    }

    public int getEditingElementId() {
        return editingElementId;
    }
    public void setEditingElementId(int editingElementId) {
        this.editingElementId = editingElementId;
    }
    public void setCreateTitlesFunction(Runnable createTitlesFunction) {
        this.createTitlesFunction = createTitlesFunction;
    }
    public void setEditTitlesFunction(Runnable editTitlesFunction) {
        this.editTitlesFunction = editTitlesFunction;
    }
    public void createTitlesRun(){ createTitlesFunction.run(); }
    public void editTitlesRun(){ editTitlesFunction.run(); }

    public JButton getSaveButton(){ return saveButton; }
    public JButton getBackButton(){ return backButton; }

    public void setFrameTitle(String text){
        this.setTitle(text);
    }
    public void setHeaderText(String text){
        header.setText(text);
    }
    public void setParam1Text(String text){
        paramLabel1.setText(text);
    }
    public void setParam2Text(String text){
        paramLabel2.setText(text);
    }
    public String getParam1Value(){ return paramInput1.getText(); }
    public void setParam1Value(String text){ paramInput1.setText(text); }
    public String getParam2Value(){ return paramInput2.getText(); }
    public void setParam2Value(String text){ paramInput2.setText(text);  }
    public void clearInputs() {
        paramInput1.setText("");
        paramInput2.setText("");
    }
    public void Show(){
        update();
        this.setVisible(true);
    }
    private void update(){
        this.revalidate();
        this.repaint();
    }

    public void createThemeTitles(){
        setFrameTitle("Тема");
        setHeaderText("Создание темы");
        setParam1Text("Название");
        setParam2Text("Описание");
    }
    public void editThemeTitles(){
        setFrameTitle("Тема");
        setHeaderText("Изменение темы");
        setParam1Text("Название");
        setParam2Text("Описание");
    }
    public void createPacketTitles(){
        setFrameTitle("Пакет");
        setHeaderText("Создание пакета");
        setParam1Text("Название");
        setParam2Text("Описание");
    }
    public void editPacketTitles(){
        setFrameTitle("Пакет");
        setHeaderText("Изменение пакета");
        setParam1Text("Название");
        setParam2Text("Описание");
    }
    public void createCardTitles(){
        setFrameTitle("Карточка");
        setHeaderText("Создание карточки");
        setParam1Text("Термин");
        setParam2Text("Определение");
    }
    public void editCardTitles(){
        setFrameTitle("Карточка");
        setHeaderText("Изменение карточки");
        setParam1Text("Термин");
        setParam2Text("Определение");
    }
}
