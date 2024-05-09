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
        super();
        setSize(316, 375);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

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

        update();
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
    private void update(){
        this.revalidate();
        this.repaint();
    }
}
