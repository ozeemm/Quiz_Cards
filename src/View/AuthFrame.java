package View;

import Model.Style;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AuthFrame extends JFrame {
    private JPanel mainPanel;
    private JLabel header;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JButton loginButton;

    public AuthFrame(){
        super();
        setSize(250, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        mainPanel = new JPanel(new MigLayout("insets 10"));
        mainPanel.setBackground(Style.getPanelBackgroundColor());

        header = new JLabel("Quiz Cards Admin");
        header.setFont(Style.getSmallHeaderFont());
        header.setForeground(Style.getTextColor());
        header.setHorizontalAlignment(JLabel.CENTER);;

        emailLabel = new JLabel();
        emailLabel.setFont(Style.getInputLabelFont());
        emailLabel.setForeground(Style.getTextColor());
        emailLabel.setText("Почта");

        emailInput = new JTextField(30);
        emailInput.setBackground(Style.getInputBackgroundColor());
        emailInput.setFont(Style.getInputFont());
        emailInput.setForeground(Style.getTextColor());

        passwordLabel = new JLabel();
        passwordLabel.setFont(Style.getInputLabelFont());
        passwordLabel.setForeground(Style.getTextColor());
        passwordLabel.setText("Пароль");

        passwordInput = new JPasswordField(30);
        passwordInput.setBackground(Style.getInputBackgroundColor());
        passwordInput.setFont(Style.getInputFont());
        passwordInput.setForeground(Style.getTextColor());

        loginButton = new JButton("Войти");
        loginButton.setFont(Style.getSmallButtonFont());
        loginButton.setBackground(Style.getButtonBackgroundColor());
        loginButton.setForeground(Style.getButtonTextColor());

        mainPanel.add(header, "span, grow, gapy 0 5");
        mainPanel.add(emailLabel, "wrap");
        mainPanel.add(emailInput, "span, grow");
        mainPanel.add(passwordLabel, "wrap");
        mainPanel.add(passwordInput, "span, grow");
        mainPanel.add(loginButton, "gapleft push, gapright push, gapy 25 0");
        this.add(mainPanel);

        update();
    }
    private void update(){
        this.revalidate();
        this.repaint();
    }

    public JButton getLoginButton(){ return loginButton; }
    public String getEmail(){ return emailInput.getText(); }
    public String getPassword(){ return passwordInput.getText(); }
}
