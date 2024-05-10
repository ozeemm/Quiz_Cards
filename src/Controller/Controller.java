package Controller;

import Data.Repository;
import View.*;

import javax.swing.*;

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
        editThemeFrame.setFrameTitle("Изменение темы");
        editThemeFrame.setTitle("Изменение темы");
        editThemeFrame.setParam1Text("Название");
        editThemeFrame.setParam2Text("Описание");
        editThemeFrame.getSaveButton().addActionListener(e -> { editThemeFrame.setVisible(false); });
        editThemeFrame.getBackButton().addActionListener(e -> { editThemeFrame.setVisible(false); });
        editThemeFrame.getSaveButton().addActionListener(e -> {
            // Проверка на пустые строки
            if(editThemeFrame.getParam1Value().isEmpty() || editThemeFrame.getParam1Value().isEmpty())
                return;

            if(editThemeFrame.getEditingElementId() == -1){
                // Создание
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
        editPacketFrame.setFrameTitle("Изменение пакета");
        editPacketFrame.setTitle("Изменение пакета");
        editPacketFrame.setParam1Text("Название");
        editPacketFrame.setParam2Text("Описание");
        editPacketFrame.getSaveButton().addActionListener(e -> { editPacketFrame.setVisible(false); });
        editPacketFrame.getBackButton().addActionListener(e -> { editPacketFrame.setVisible(false); });
        editPacketFrame.getSaveButton().addActionListener(e ->{
            // Проверка на пустые строки
            if(editPacketFrame.getParam1Value().isEmpty() || editPacketFrame.getParam1Value().isEmpty())
                return;

            if(editPacketFrame.getEditingElementId() == -1){
                // Создание
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
        editCardFrame.setFrameTitle("Изменение карточки");
        editCardFrame.setTitle("Изменение карточки");
        editCardFrame.setParam1Text("Термин");
        editCardFrame.setParam2Text("Определение");
        editCardFrame.getSaveButton().addActionListener(e -> { editCardFrame.setVisible(false); });
        editCardFrame.getBackButton().addActionListener(e -> { editCardFrame.setVisible(false); });
        editCardFrame.getSaveButton().addActionListener(e -> {
            // Проверка на пустые строки
            if(editCardFrame.getParam1Value().isEmpty() || editCardFrame.getParam1Value().isEmpty())
                return;

            if(editCardFrame.getEditingElementId() == -1){
                // Создание
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

        // Кнопки "Назад"
        mainFrame.getThemesPanel().getBackButton().addActionListener(e -> {
            System.exit(0);
        });
        mainFrame.getPacketsPanel().getBackButton().addActionListener(e -> {
            mainFrame.OpenPanel(mainFrame.getThemesPanel());
        });
        mainFrame.getCardsPanel().getBackButton().addActionListener(e -> {
            mainFrame.OpenPanel(mainFrame.getPacketsPanel());
        });
    }

    private void updateThemeButtons(){
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

                editThemeFrame.setParam1Value(repository.getTheme(index).getName());
                editThemeFrame.setParam2Value(repository.getTheme(index).getDescription());
                editThemeFrame.setEditingElementId(index);
                editThemeFrame.Show();
            });
        }

        // Кнопки "Удалить"
    }
    private void updatePacketButtons(){
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

                editPacketFrame.setParam1Value(repository.getPacket(index).getName());
                editPacketFrame.setParam2Value(repository.getPacket(index).getDescription());
                editPacketFrame.setEditingElementId(index);
                editPacketFrame.Show();
            });
        }

        // Кнопки "Удалить"
    }
    private void updateCardButtons(){
        // Кнопки "Изменить"
        for(JButton button : mainFrame.getCardsPanel().getEditButtons()){
            button.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                int index = mainFrame.getCardsPanel().getEditButtons().indexOf(b);

                editCardFrame.setParam1Value(repository.getCard(index).getFrontText());
                editCardFrame.setParam2Value(repository.getCard(index).getBackText());
                editCardFrame.setEditingElementId(index);
                editCardFrame.Show();
            });
        }

        // Кнопки "Удалить"
    }
}
