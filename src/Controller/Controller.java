package Controller;

import Data.Repository;
import Interfaces.*;
import View.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Controller {
    private Repository repository;
    private MainFrame mainFrame;
    private EditFrame editThemeFrame;
    private EditFrame editPacketFrame;
    private EditFrame editCardFrame;

    public void start(){
        repository = new Repository();

        mainFrame = new MainFrame();

        // Окно изменения темы
        editThemeFrame = new EditFrame();
        editThemeFrame.setCreateTitlesFunction(editThemeFrame::createThemeTitles);
        editThemeFrame.setEditTitlesFunction(editThemeFrame::editThemeTitles);
        editThemeFrame.getSaveButton().addActionListener(e -> {
            editFrameSaveButtonFunction(editThemeFrame,
                                        repository::createTheme,
                                        repository::updateTheme,
                                        mainFrame.getThemesPanel(),
                                        repository::getThemeNames,
                                        this::updateThemeButtons);
        });

        // Окно изменения пакета
        editPacketFrame = new EditFrame();
        editPacketFrame.setCreateTitlesFunction(editPacketFrame::createPacketTitles);
        editPacketFrame.setEditTitlesFunction(editPacketFrame::editPacketTitles);
        editPacketFrame.getSaveButton().addActionListener(e ->{
            editFrameSaveButtonFunction(editPacketFrame,
                    repository::createPacket,
                    repository::updatePacket,
                    mainFrame.getPacketsPanel(),
                    repository::getPacketNames,
                    this::updatePacketButtons);
        });

        // Окно изменения карточки
        editCardFrame = new EditFrame();
        editCardFrame.setCreateTitlesFunction(editCardFrame::createCardTitles);
        editCardFrame.setEditTitlesFunction(editCardFrame::editCardTitles);
        editCardFrame.getSaveButton().addActionListener(e -> {
            editFrameSaveButtonFunction(editCardFrame,
                    repository::createCard,
                    repository::updateCard,
                    mainFrame.getCardsPanel(),
                    repository::getCardNames,
                    this::updateCardButtons);
        });

        // Стартовое окно
        mainFrame.getThemesPanel().setElements(repository.getThemeNames());
        mainFrame.OpenPanel(mainFrame.getThemesPanel());
        updateThemeButtons();

        // Кнопка "Назад"
        mainFrame.getHeaderPanel().getBackButton().addActionListener(e ->{
            // Темы
            if(mainFrame.getCurrentPanel() == mainFrame.getThemesPanel()){
                System.exit(0);
            }
            // Пакеты
            else if(mainFrame.getCurrentPanel() == mainFrame.getPacketsPanel()){
                mainFrame.OpenPanel(mainFrame.getThemesPanel());
                updatePacketButtons();
                updateElementsCount(mainFrame.getThemesPanel(), repository::getPacketsCount, "Пакетов");
            }
            // Карточки
            else if(mainFrame.getCurrentPanel() == mainFrame.getCardsPanel()){
                mainFrame.OpenPanel(mainFrame.getPacketsPanel());
                updateCardButtons();
                updateElementsCount(mainFrame.getPacketsPanel(), repository::getCardsCount, "Карточек");
            }
        });

        // Кнопка "Создать"
        mainFrame.getHeaderPanel().getAddButton().addActionListener(e ->{
            EditFrame editFrame = new EditFrame();
            if(mainFrame.getCurrentPanel() == mainFrame.getThemesPanel()){
                editFrame = editThemeFrame;
            }
            else if(mainFrame.getCurrentPanel() == mainFrame.getPacketsPanel()){
                editFrame = editPacketFrame;
            }
            else if(mainFrame.getCurrentPanel() == mainFrame.getCardsPanel()){
                editFrame = editCardFrame;
            }

            editFrame.createTitlesRun();
            editFrame.setEditingElementId(-1);
            editFrame.clearInputs();
            editFrame.Show();
        });
    }

    private void updateThemeButtons(){
        updateElementButtons(mainFrame.getThemesPanel(),
                mainFrame.getPacketsPanel(),
                repository::getPacketNames,
                this::updatePacketButtons);

        updateEditButtons(mainFrame.getThemesPanel(),
                editThemeFrame,
                (index) -> repository.getTheme(index).getName(),
                (index) -> repository.getTheme(index).getDescription());

        updateDeleteButtons(mainFrame.getThemesPanel(),
                repository::deleteTheme,
                repository::getThemeNames,
                this::updateThemeButtons);
        updateElementsCount(mainFrame.getThemesPanel(), repository::getPacketsCount,"Пакетов");
    }
    private void updatePacketButtons(){
        updateElementButtons(mainFrame.getPacketsPanel(),
                mainFrame.getCardsPanel(),
                repository::getCardNames,
                this::updateCardButtons);

        updateEditButtons(mainFrame.getPacketsPanel(),
                editPacketFrame,
                (index) -> repository.getPacket(index).getName(),
                (index) -> repository.getPacket(index).getDescription());

        updateDeleteButtons(mainFrame.getPacketsPanel(),
                repository::deletePacket,
                repository::getPacketNames,
                this::updatePacketButtons);
        updateElementsCount(mainFrame.getPacketsPanel(), repository::getCardsCount, "Карточек");
    }
    private void updateCardButtons(){
        updateEditButtons(mainFrame.getCardsPanel(),
                editCardFrame,
                (index) -> repository.getCard(index).getFrontText(),
                (index) -> repository.getCard(index).getBackText());

        updateDeleteButtons(mainFrame.getCardsPanel(),
                repository::deleteCard,
                repository::getCardNames,
                this::updateCardButtons);

    }

    private void updateElementButtons(GroupPanel groupPanel,
                                      GroupPanel openingPanel,
                                      Function<Integer, String[]> getNames,
                                      Runnable updateOpeningPanelButtons){
        for(JButton button : groupPanel.getElementButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = groupPanel.getElementButtons().indexOf(b);

                openingPanel.setElements(getNames.apply(index));
                mainFrame.OpenPanel(openingPanel);
                updateOpeningPanelButtons.run();
            });
        }
    }
    private void updateEditButtons(GroupPanel groupPanel,
                                   EditFrame editFrame,
                                   Function<Integer, String> param1GetDefault,
                                   Function<Integer, String> param2GetDefault){
        for(JButton button : groupPanel.getEditButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = groupPanel.getEditButtons().indexOf(b);

                editFrame.editTitlesRun();
                editFrame.setParam1Value(param1GetDefault.apply(index));
                editFrame.setParam2Value(param2GetDefault.apply(index));
                editFrame.setEditingElementId(index);
                editFrame.Show();
            });
        }
    }
    private void updateDeleteButtons(GroupPanel groupPanel,
                                     Consumer<Integer> deleteMethod,
                                     Supplier<String[]> getNames,
                                     Runnable updateMethod){
        for(JButton button : groupPanel.getDeleteButtons()){
            button.addActionListener(e ->{
                JButton b = (JButton) e.getSource();
                int index = groupPanel.getDeleteButtons().indexOf(b);

                deleteMethod.accept(index);
                groupPanel.setElements(getNames.get());
                updateMethod.run();
            });
        }
    }
    private void updateElementsCount(GroupPanel groupPanel,
                                     Function<Integer, Integer> getCount,
                                     String paramName){
        ArrayList<GroupElement> elements = groupPanel.getElements();
        for(int i = 0; i < elements.size(); i++) {
            elements.get(i).setElementsCountText(paramName + ": " + getCount.apply(i));
        }
    }
    private void editFrameSaveButtonFunction(EditFrame editFrame,
                                             BiConsumer<String, String> createMethod,
                                             TriConsumer<Integer, String, String> updateMethod,
                                             GroupPanel currentPanel,
                                             Supplier<String[]> getNames,
                                             Runnable updateButtonsMethod){
        if(editFrame.getParam1Value().isEmpty() || editFrame.getParam1Value().isEmpty())
            return;

        if(editFrame.getEditingElementId() == -1){
            createMethod.accept(editFrame.getParam1Value(),
                                editFrame.getParam2Value());
        }
        else {
            updateMethod.accept(editFrame.getEditingElementId(),
                                editFrame.getParam1Value(),
                                editFrame.getParam2Value());
        }
        currentPanel.setElements(getNames.get());
        updateButtonsMethod.run();
    }
}
