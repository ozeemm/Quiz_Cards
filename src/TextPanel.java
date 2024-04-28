import java.util.Scanner;

public class TextPanel {
    private String title;
    private String[] variants;
    private boolean isBackVariant = true; // Есть ли вариант вернуться назад?

    public TextPanel(){
        title = "Title";
        variants = new String[] { "Вариант1", "Вариант2" };
        isBackVariant = true;
    }
    public TextPanel(String title, String[] variants){
        setTitle(title);
        setVariants(variants);
    }
    public TextPanel(String title, String[] variants, boolean isBackVariant){
        setTitle(title);
        setVariants(variants);
        setIsBackVariant(isBackVariant);
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setVariants(String[] variants){
        this.variants = variants.clone();
    }
    public void setIsBackVariant(boolean isBackVariant){
        this.isBackVariant = isBackVariant;
    }

    // Вывод заголовка с украшением
    private String getFormattedTitle(){
        final char highlightChar = '='; // Символ для выделения заголовка
        final int highlightCharsNum = 7; // Количество таких символов в заголовке с каждой из сторон

        StringBuilder formattedTitle = new StringBuilder(); // Для сборки строк лучше использовать его
        for(int i = 0; i < highlightCharsNum; i++){
            formattedTitle.append(highlightChar);
        }
        formattedTitle.append(" " + title + " ");
        for(int i = 0; i < highlightCharsNum; i++){
            formattedTitle.append(highlightChar);
        }
        return formattedTitle.toString();
    }
    // Вывод элементов меню
    private void printPanel(){
        if(title != null) {
            System.out.println(getFormattedTitle());
        }
        for(int i = 0; i < variants.length; i++){
            System.out.println((i+1) + ". " + variants[i]);
        }
        if(isBackVariant){
            System.out.println("0. Назад");
        }
    }
    // Считываем выбранный вариант
    private int readChoice(){
        Scanner sc = new Scanner(System.in);
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine()); // Для очистки буфера сканера
        }
        catch (NumberFormatException ex){
            System.out.println("Введите число");
            return readChoice();
        }

        if((choice > 0 && choice <= variants.length) || (choice == 0 && isBackVariant)){
            return choice;
        }
        else {
            System.out.println("Вводите только значения пунктов меню");
            return readChoice();
        }
    }
    public int getChoice() {
        printPanel();
        return readChoice();
    }

    // Считывание положительного числа
    public static int readInt(String title){
        System.out.print(title + ": ");
        Scanner sc = new Scanner(System.in);
        int number;
        try {
            number = Integer.parseInt(sc.nextLine()); // Для очистки буфера сканера
        }
        catch (NumberFormatException ex){
            System.out.println("Введите число");;
            return readInt(title);
        }

        if(number >= 0){
            return number;
        }
        else {
            System.out.println("Число должно быть неотрицательным");
            return readInt(title);
        }
    }
    public static float readFloat(String title){
        System.out.print(title + ": ");
        Scanner sc = new Scanner(System.in);
        float number;
        try {
            number = Float.parseFloat(sc.nextLine()); // Для очистки буфера сканера
        }
        catch (NumberFormatException ex){
            System.out.println("Введите число");;
            return readFloat(title);
        }

        if(number >= 0){
            return number;
        }
        else {
            System.out.println("Число должно быть неотрицательным");
            return readFloat(title);
        }
    }
    public static String readString(String title){
        System.out.print(title + ": ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }
    public static boolean readBool(String title){
        System.out.print(title + ": ");
        Scanner sc = new Scanner(System.in);
        String val = sc.nextLine().toLowerCase();

        if(val.equals("true"))
            return true;
        else if(val.equals("false"))
            return false;
        else{
            System.out.println("Вводите только true/false");
            return readBool(title);
        }
    }
    public static boolean readYesOrNo(String title){
        System.out.print(title + ": ");
        Scanner sc = new Scanner(System.in);
        String val = sc.nextLine().toLowerCase();

        if(val.equals("да") || val.equals("true"))
            return true;
        else if(val.equals("нет") || val.equals("false"))
            return false;
        else{
            System.out.println("Вводите только да/нет или true/false");
            return readYesOrNo(title);
        }
    }
}