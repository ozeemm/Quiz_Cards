package View;

import Controller.Controller;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        super("Quiz Cards Admin"); // Окно с названием
        setSize(950, 750); // Размер окна
        setResizable(false); // Запрещаем растяжение окна
        setLocationRelativeTo(null); // Чтобы окно открылось в центре экрана
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        setVisible(true); // Включаем окно
        //ImageIcon icon = new ImageIcon("./img/icon2.png");
        //this.setIconImage(icon.getImage()); // Иконка окна

        this.revalidate();
        this.repaint();
    }
}
