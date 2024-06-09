package View;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GroupPanel extends JPanel {
    private final ArrayList<GroupElement> elements = new ArrayList<>();
    private final JPanel elementsPanel;
    private final JScrollPane scroll;

    public GroupPanel(){
        super(new GridLayout()); // т.к. растягивается

        elementsPanel = new JPanel(new MigLayout("wrap, insets 5", "[]10[]10[]", "[]10"));
        elementsPanel.setBackground(Style.getPanelBackgroundColor());

        scroll = new JScrollPane(elementsPanel);

        this.add(scroll);

        update();
    }

    public ArrayList<GroupElement> getElements(){ return elements; }
    public ArrayList<JButton> getElementButtons(){
        return new ArrayList<>(elements.stream().map(e -> e.getElementButton()).toList());
    }
    public ArrayList<JButton> getEditButtons(){
        return new ArrayList<>(elements.stream().map(e -> e.getEditButton()).toList());
    }
    public ArrayList<JButton> getDeleteButtons(){
        return new ArrayList<>(elements.stream().map(e -> e.getDeleteButton()).toList());
    }

    public void setElements(String[] names){
        clearElements();

        int i = 0;
        for(String name : names){
            GroupElement element = new GroupElement();
            element.setElementName(name);
            element.setElementId(i);

            elements.add(element);
            elementsPanel.add(element);
        }

        update();
    }
    private void clearElements(){
        for(GroupElement element : elements){
            elementsPanel.remove(element);
        }
        elements.clear();
    }

    private void update(){
        this.revalidate();
        this.repaint();
    }
}
