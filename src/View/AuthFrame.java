package View;

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
    private final Color backgroundColor = new Color(238, 238, 238);
    private final Color inputBackgroundColor = Color.WHITE;
    private final Color textColor = Color.BLACK;
    private final Color buttonColor = Color.LIGHT_GRAY;
    private final Color buttonTextColor = Color.BLACK;
    private final Font headerFont = new Font("Arial", Font.BOLD, 18);
    private final Font textFont = new Font("Arial", Font.PLAIN, 14);
    private final Font inputFont = new Font("Arial", Font.PLAIN, 13);

    public AuthFrame(){
        super();
        setSize(250, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        mainPanel = new JPanel(new MigLayout("insets 10"));
        mainPanel.setBackground(backgroundColor);

        header = new JLabel("Quiz Cards Admin");
        header.setFont(headerFont);
        header.setForeground(textColor);
        header.setHorizontalAlignment(JLabel.CENTER);;

        emailLabel = new JLabel();
        emailLabel.setFont(textFont);
        emailLabel.setForeground(textColor);
        emailLabel.setText("Почта");

        emailInput = new JTextField(30);
        emailInput.setBackground(inputBackgroundColor);
        emailInput.setFont(inputFont);
        emailInput.setForeground(textColor);

        passwordLabel = new JLabel();
        passwordLabel.setFont(textFont);
        passwordLabel.setForeground(textColor);
        passwordLabel.setText("Пароль");

        passwordInput = new JPasswordField(30);
        passwordInput.setBackground(inputBackgroundColor);
        passwordInput.setFont(inputFont);
        passwordInput.setForeground(textColor);

        loginButton = new JButton("Войти");
        loginButton.setFont(textFont);
        loginButton.setBackground(buttonColor);
        loginButton.setForeground(buttonTextColor);

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
