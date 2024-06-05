package View;

import java.awt.*;

public class Style {
    public static Color getPanelBackgroundColor(){ return Color.lightGray; }
    public static Color getTextColor(){ return Color.black; }
    public static Color getButtonBackgroundColor(){ return Color.gray; }
    public static Color getButtonTextColor(){ return Color.white; }
    public static Color getBorderColor(){ return Color.white; }
    public static Color getInputBackgroundColor(){ return Color.white; }

    public static Font getHeaderFont(){ return new Font("Arial", Font.BOLD, 24); }
    public static Font getSmallHeaderFont(){ return new Font("Arial", Font.BOLD, 18); }
    public static Font getButtonFont(){ return new Font("Arial", Font.PLAIN, 16); }
    public static Font getSmallButtonFont(){ return new Font("Arial", Font.PLAIN, 14); }
    public static Font getElementInfoFont(){ return new Font("Arial", Font.ITALIC, 14); }
    public static Font getInputLabelFont(){ return new Font("Arial", Font.PLAIN, 14); }
    public static Font getInputFont(){ return new Font("Arial", Font.PLAIN, 13); }
}
