package View;

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

    protected JPanel getMainPanel(){ return mainPanel; }

    public EditFrame(){
        super();
        setSize(250, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        header = new JLabel("Изменение");

        paramLabel1 = new JLabel("param1");

        paramInput1 = new JTextField();
        paramInput1.setMaximumSize(new Dimension(300, 50));

        paramLabel2 = new JLabel("param2");

        paramInput2 = new JTextArea();
        paramInput2.setLineWrap(true);
        paramInput2.setWrapStyleWord(true);

        scrollPane = new JScrollPane(paramInput2);

        saveButton = new JButton("Сохранить");
        backButton = new JButton("Назад");

        mainPanel.add(header);
        mainPanel.add(paramLabel1);
        mainPanel.add(paramInput1);
        mainPanel.add(paramLabel2);
        mainPanel.add(scrollPane);
        mainPanel.add(saveButton);
        mainPanel.add(backButton);
        this.add(mainPanel);

        update();
    }

    public int getEditingElementId() {
        return editingElementId;
    }
    public void setEditingElementId(int editingElementId) {
        this.editingElementId = editingElementId;
    }

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
    public void ClearInputs() {
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
}
