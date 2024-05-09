package View;

import javax.swing.*;
import java.awt.*;

public class EditCardFrame extends EditFrame{
    private JButton deleteButton;
    public EditCardFrame(){
        super();

        deleteButton = new JButton("Удалить");

        // Добавляем кнопку "удалить" перед кнопкой "назад"
        int componentsLength = getMainPanel().getComponentCount();
        Component lastComponent = getMainPanel().getComponent(componentsLength-1);

        getMainPanel().add(deleteButton);
        getMainPanel().add(lastComponent);

        this.Update();
    }
}
