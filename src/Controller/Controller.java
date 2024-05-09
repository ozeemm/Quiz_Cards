package Controller;

import Data.Repository;
import View.*;

import javax.swing.*;

public class Controller {
    private Repository repository;
    private MainFrame mainFrame;
    public void Start(){
        repository = new Repository();

        mainFrame = new MainFrame();
        mainFrame.getThemesPanel().setThemes(repository.getThemeNames());
        mainFrame.OpenPanel(mainFrame.getThemesPanel());

        UpdateThemeButtons();

        //EditCardFrame editFrame = new EditCardFrame();
    }

    private void UpdateThemeButtons(){
        for(JButton button : mainFrame.getThemesPanel().getThemeButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getThemesPanel().getThemeButtons().indexOf(b);
                mainFrame.getPacketsPanel().setPackets(repository.getPacketNames(index));
                mainFrame.OpenPanel(mainFrame.getPacketsPanel());
                UpdatePacketButtons();
            });
        }
    }

    private void UpdatePacketButtons(){
        for(JButton button : mainFrame.getPacketsPanel().getPacketButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getPacketsPanel().getPacketButtons().indexOf(b);
                mainFrame.getCardsPanel().setCards(repository.getCardNames(index));
                mainFrame.OpenPanel(mainFrame.getCardsPanel());
            });
        }
    }

    private void UpdateCardButtons(){

    }
}
