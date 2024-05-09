package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PacketsPanel extends JPanel {
    private ArrayList<JButton> packetButtons = new ArrayList<JButton>();
    public PacketsPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel header = new JLabel("Пакеты");
        header.setFont(new Font("Arial", Font.BOLD, 36));

        this.add(header);
    }

    public ArrayList<JButton> getPacketButtons(){ return packetButtons; }

    public void setPackets(String[] packetNames){
        packetButtons.clear();
        for(String packetName : packetNames){
            JButton button = new JButton(packetName);
            packetButtons.add(button);

            this.add(button);
            this.add(new JButton("Изменить"));
            this.add(new JButton("Удалить"));
        }
        Update();
    }

    private void Update(){
        this.revalidate();
        this.repaint();
    }
}
