package Controller;

import Data.Repository;
import View.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.function.Function;

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
                mainFrame.getThemesPanel().setElements(repository.getThemeNames());
                updateThemeButtons();
            }
            else {
                // Изменение
                repository.updateTheme(editThemeFrame.getEditingElementId(),
                                        editThemeFrame.getParam1Value(),
                                        editThemeFrame.getParam2Value());
                mainFrame.getThemesPanel().setElements(repository.getThemeNames());
                updateThemeButtons();
            }
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
                mainFrame.getPacketsPanel().setElements(repository.getPacketNames());
                updatePacketButtons();
            }
            else {
                // Изменение
                repository.updatePacket(editPacketFrame.getEditingElementId(),
                                        editPacketFrame.getParam1Value(),
                                        editPacketFrame.getParam2Value());
                mainFrame.getPacketsPanel().setElements(repository.getPacketNames());
                updatePacketButtons();
            }
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
                mainFrame.getCardsPanel().setElements(repository.getCardNames());
                updateCardButtons();
            }
            else {
                // Изменение
                repository.updateCard(editCardFrame.getEditingElementId(),
                        editCardFrame.getParam1Value(),
                        editCardFrame.getParam2Value());
                mainFrame.getCardsPanel().setElements(repository.getCardNames());
                updateCardButtons();
            }
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
    private void updateThemeButtons(){
        updateElementsCount(mainFrame.getThemesPanel(), repository::getPacketsCount,"Пакетов");
        // Кнопки открытия тем
        for(JButton button : mainFrame.getThemesPanel().getElementButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getThemesPanel().getElementButtons().indexOf(b);
                mainFrame.getPacketsPanel().setElements(repository.getPacketNames(index));
                mainFrame.OpenPanel(mainFrame.getPacketsPanel());
                updatePacketButtons();
            });
        }

        // Кнопки "Изменить"
        for(JButton button : mainFrame.getThemesPanel().getEditButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getThemesPanel().getEditButtons().indexOf(b);

                editThemeFrame.editThemeTitles();
                editThemeFrame.setParam1Value(repository.getTheme(index).getName());
                editThemeFrame.setParam2Value(repository.getTheme(index).getDescription());
                editThemeFrame.setEditingElementId(index);
                editThemeFrame.Show();
            });
        }

        // Кнопки "Удалить"
        for(JButton button : mainFrame.getThemesPanel().getDeleteButtons()){
            button.addActionListener(e ->{
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getThemesPanel().getDeleteButtons().indexOf(b);
                repository.deleteTheme(index);
                mainFrame.getThemesPanel().setElements(repository.getThemeNames());
                updateThemeButtons();
            });
        }
    }
    private void updatePacketButtons(){
        updateElementsCount(mainFrame.getPacketsPanel(), repository::getCardsCount, "Карточек");
        // Кнопки открытия пакетов
        for(JButton button : mainFrame.getPacketsPanel().getElementButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getPacketsPanel().getElementButtons().indexOf(b);
                mainFrame.getCardsPanel().setElements(repository.getCardNames(index));
                mainFrame.OpenPanel(mainFrame.getCardsPanel());
                updateCardButtons();
            });
        }

        // Кнопки "Изменить"
        for(JButton button : mainFrame.getPacketsPanel().getEditButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getPacketsPanel().getEditButtons().indexOf(b);

                editPacketFrame.editPacketTitles();
                editPacketFrame.setParam1Value(repository.getPacket(index).getName());
                editPacketFrame.setParam2Value(repository.getPacket(index).getDescription());
                editPacketFrame.setEditingElementId(index);
                editPacketFrame.Show();
            });
        }

        // Кнопки "Удалить"
        for(JButton button : mainFrame.getPacketsPanel().getDeleteButtons()){
            button.addActionListener(e ->{
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getPacketsPanel().getDeleteButtons().indexOf(b);

                repository.deletePacket(index);
                mainFrame.getPacketsPanel().setElements(repository.getPacketNames());
                updatePacketButtons();
            });
        }
    }
    private void updateCardButtons(){
        // Кнопки "Изменить"
        for(JButton button : mainFrame.getCardsPanel().getEditButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getCardsPanel().getEditButtons().indexOf(b);

                editCardFrame.editCardTitles();
                editCardFrame.setParam1Value(repository.getCard(index).getFrontText());
                editCardFrame.setParam2Value(repository.getCard(index).getBackText());
                editCardFrame.setEditingElementId(index);
                editCardFrame.Show();
            });
        }

        // Кнопки "Удалить"
        for(JButton button : mainFrame.getCardsPanel().getDeleteButtons()){
            button.addActionListener(e ->{
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getCardsPanel().getDeleteButtons().indexOf(b);

                repository.deleteCard(index);
                mainFrame.getCardsPanel().setElements(repository.getCardNames());
                updateCardButtons();
            });
        }
    }
    private void updateElementsCount(GroupPanel groupPanel,  Function<Integer, Integer> getCount, String paramName){
        ArrayList<GroupElement> elements = groupPanel.getElements();
        for(int i = 0; i < elements.size(); i++) {
            elements.get(i).setElementsCountText(paramName + ": " + getCount.apply(i));
        }
    }
}
