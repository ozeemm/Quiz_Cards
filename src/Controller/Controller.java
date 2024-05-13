package Controller;

import Data.Repository;
import View.*;

import javax.swing.*;
import java.util.ArrayList;
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
        editThemeFrame.getSaveButton().addActionListener(e -> {
            // Проверка на пустые строки
            if(editThemeFrame.getParam1Value().isEmpty() || editThemeFrame.getParam1Value().isEmpty())
                return;

            if(editThemeFrame.getEditingElementId() == -1){
                // Создание
                repository.createTheme(editThemeFrame.getParam1Value(),
                                        editThemeFrame.getParam2Value());
            }
            else {
                // Изменение
                repository.updateTheme(editThemeFrame.getEditingElementId(),
                                        editThemeFrame.getParam1Value(),
                                        editThemeFrame.getParam2Value());
            }
            mainFrame.getThemesPanel().setElements(repository.getThemeNames());
            updateThemeButtons();
        });

        // Окно изменения пакета
        editPacketFrame = new EditFrame();
        editPacketFrame.getSaveButton().addActionListener(e ->{
            // Проверка на пустые строки
            if(editPacketFrame.getParam1Value().isEmpty() || editPacketFrame.getParam1Value().isEmpty())
                return;

            if(editPacketFrame.getEditingElementId() == -1){
                // Создание
                repository.createPacket(editPacketFrame.getParam1Value(),
                        editPacketFrame.getParam2Value());
            }
            else {
                // Изменение
                repository.updatePacket(editPacketFrame.getEditingElementId(),
                                        editPacketFrame.getParam1Value(),
                                        editPacketFrame.getParam2Value());
            }
            mainFrame.getPacketsPanel().setElements(repository.getPacketNames());
            updatePacketButtons();
        });

        // Окно изменения карточки
        editCardFrame = new EditFrame();
        editCardFrame.getSaveButton().addActionListener(e -> {
            // Проверка на пустые строки
            if(editCardFrame.getParam1Value().isEmpty() || editCardFrame.getParam1Value().isEmpty())
                return;

            if(editCardFrame.getEditingElementId() == -1){
                // Создание
                repository.createCard(editCardFrame.getParam1Value(),
                                        editCardFrame.getParam2Value());
            }
            else {
                // Изменение
                repository.updateCard(editCardFrame.getEditingElementId(),
                        editCardFrame.getParam1Value(),
                        editCardFrame.getParam2Value());
            }
            mainFrame.getCardsPanel().setElements(repository.getCardNames());
            updateCardButtons();
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
                updateElementsCount(mainFrame.getThemesPanel(), repository::getPacketsCount, "Пакетов");
            }
            // Карточки
            else if(mainFrame.getCurrentPanel() == mainFrame.getCardsPanel()){
                mainFrame.OpenPanel(mainFrame.getPacketsPanel());
                updateElementsCount(mainFrame.getPacketsPanel(), repository::getCardsCount, "Карточек");
            }
        });

        // Кнопка "Создать"
        mainFrame.getHeaderPanel().getAddButton().addActionListener(e ->{
            // Темы
            if(mainFrame.getCurrentPanel() == mainFrame.getThemesPanel()){
                editThemeFrame.createThemeTitles();
                editThemeFrame.setEditingElementId(-1);
                editThemeFrame.clearInputs();
                editThemeFrame.Show();
            }
            // Пакеты
            else if(mainFrame.getCurrentPanel() == mainFrame.getPacketsPanel()){
                editPacketFrame.createPacketTitles();
                editPacketFrame.setEditingElementId(-1);
                editPacketFrame.clearInputs();
                editPacketFrame.Show();
            }
            // Карточки
            else if(mainFrame.getCurrentPanel() == mainFrame.getCardsPanel()){
                editCardFrame.createCardTitles();
                editCardFrame.setEditingElementId(-1);
                editCardFrame.clearInputs();
                editCardFrame.Show();
            }
        });
    }

    private void updateElementButtons(GroupPanel groupPanel, GroupPanel openingPanel, Function<Integer, String[]> getNames, Runnable updateOpeningPanelButtons){
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
    private void updateEditButtons(GroupPanel groupPanel, EditFrame editFrame, Runnable setEditFrameTitles, Function<Integer, String> param1GetDefault, Function<Integer, String> param2GetDefault){
        for(JButton button : groupPanel.getEditButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = groupPanel.getEditButtons().indexOf(b);

                setEditFrameTitles.run();
                editFrame.setParam1Value(param1GetDefault.apply(index));
                editFrame.setParam2Value(param2GetDefault.apply(index));
                editFrame.setEditingElementId(index);
                editFrame.Show();
            });
        }
    }
    private void updateDeleteButtons(GroupPanel groupPanel, Consumer<Integer> deleteMethod, Supplier<String[]> getNames, Runnable updateMethod){
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

    private void updateThemeButtons(){
        updateElementsCount(mainFrame.getThemesPanel(), repository::getPacketsCount,"Пакетов");

        updateElementButtons(mainFrame.getThemesPanel(),
                                mainFrame.getPacketsPanel(),
                                repository::getPacketNames,
                                this::updatePacketButtons);

        updateEditButtons(mainFrame.getThemesPanel(),
                            editThemeFrame,
                            editThemeFrame::editPacketTitles,
                (index) -> repository.getTheme(index).getName(),
                (index) -> repository.getTheme(index).getDescription());

        updateDeleteButtons(mainFrame.getThemesPanel(),
                            repository::deleteTheme,
                            repository::getThemeNames,
                            this::updateThemeButtons);
    }
    private void updatePacketButtons(){
        updateElementsCount(mainFrame.getPacketsPanel(), repository::getCardsCount, "Карточек");

        updateElementButtons(mainFrame.getPacketsPanel(),
                                mainFrame.getCardsPanel(),
                                repository::getCardNames,
                                this::updateCardButtons);

        updateEditButtons(mainFrame.getPacketsPanel(),
                            editPacketFrame,
                            editPacketFrame::editPacketTitles,
                (index) -> repository.getPacket(index).getName(),
                (index) -> repository.getPacket(index).getDescription());

        updateDeleteButtons(mainFrame.getPacketsPanel(),
                            repository::deletePacket,
                            repository::getPacketNames,
                            this::updatePacketButtons);
    }
    private void updateCardButtons(){
        updateEditButtons(mainFrame.getCardsPanel(),
                            editCardFrame,
                            editCardFrame::editCardTitles,
                (index) -> repository.getCard(index).getFrontText(),
                (index) -> repository.getCard(index).getBackText());

        updateDeleteButtons(mainFrame.getCardsPanel(),
                            repository::deleteCard,
                            repository::getCardNames,
                            this::updateCardButtons);

    }
    private void updateElementsCount(GroupPanel groupPanel,  Function<Integer, Integer> getCount, String paramName){
        ArrayList<GroupElement> elements = groupPanel.getElements();
        for(int i = 0; i < elements.size(); i++) {
            elements.get(i).setElementsCountText(paramName + ": " + getCount.apply(i));
        }
    }
}
