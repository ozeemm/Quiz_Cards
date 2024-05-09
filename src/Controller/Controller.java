package Controller;

import Data.Repository;
import View.*;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Controller {
    private Repository repository;
    private MainFrame mainFrame;
    public void start(){
        repository = new Repository();

        mainFrame = new MainFrame();
        mainFrame.getThemesPanel().setElements(repository.getThemeNames());
        mainFrame.OpenPanel(mainFrame.getThemesPanel());

        mainFrame.getPacketsPanel().getBackButton().addActionListener(e -> {
            mainFrame.OpenPanel(mainFrame.getThemesPanel());
        });
        mainFrame.getCardsPanel().getBackButton().addActionListener(e -> {
            mainFrame.OpenPanel(mainFrame.getPacketsPanel());
        });

        updateThemeButtons();
    }

    private void updateThemeButtons(){
        for(JButton button : mainFrame.getThemesPanel().getElementButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getThemesPanel().getElementButtons().indexOf(b);
                mainFrame.getPacketsPanel().setElements(repository.getPacketNames(index));
                mainFrame.OpenPanel(mainFrame.getPacketsPanel());
                updatePacketButtons();
            });
        }
    }

    private void updatePacketButtons(){
        for(JButton button : mainFrame.getPacketsPanel().getElementButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getPacketsPanel().getElementButtons().indexOf(b);
                mainFrame.getCardsPanel().setElements(repository.getCardNames(index));
                mainFrame.OpenPanel(mainFrame.getCardsPanel());
            });
        }
    }

    private void updateCardButtons(){

    }

    /*
    private void removeButtonListeners(JButton button){
        for(ActionListener al : button.getActionListeners()){
            button.removeActionListener(al);
        }
    }
    */
}
