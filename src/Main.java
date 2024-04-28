import java.util.ArrayList;

public class Main {
    public static ArrayList<Theme> themes = new ArrayList<>();
    public static void main(String[] args) {

        Theme theme1 = new Theme();
        theme1.setName("Английский");
        Theme theme2 = new Theme();
        theme2.setName("Обществознание");

        CardsPacket cardsPacket1 = new CardsPacket();
        cardsPacket1.setName("Животные");
        CardsPacket cardsPacket2 = new CardsPacket();
        cardsPacket2.setName("Профессии");
        CardsPacket cardsPacket3 = new CardsPacket();
        cardsPacket3.setName("Право");

        Card card1 = new Card();
        card1.setFrontText("cat");
        card1.setBackText("кошка");

        Card card2 = new Card();
        card1.setFrontText("dog");
        card1.setBackText("собака");

        Card card3 = new Card();
        card1.setFrontText("rabbit");
        card1.setBackText("кролик");

        Card card4 = new Card();
        card1.setFrontText("teacher");
        card1.setBackText("учитель");

        Card card5 = new Card();
        card1.setFrontText("driver");
        card1.setBackText("водитель");

        Card card6 = new Card();
        card1.setFrontText("baker");
        card1.setBackText("пекарь");

        Card card7 = new Card();
        card1.setFrontText("Частное право");
        card1.setBackText("право, регулирующее отношения между индивидуумами, коллективами");

        Card card8 = new Card();
        card1.setFrontText("Завещание");
        card1.setBackText("односторонняя сделка, распоряжение своим имуществом на случай смерти");

        Card card9 = new Card();
        card1.setFrontText("Суд");
        card1.setBackText("орган государства, осуществляющий правосудие");

        cardsPacket1.getCards().add(card1);
        cardsPacket1.getCards().add(card2);
        cardsPacket1.getCards().add(card3);
        cardsPacket2.getCards().add(card4);
        cardsPacket2.getCards().add(card5);
        cardsPacket2.getCards().add(card6);
        cardsPacket3.getCards().add(card7);
        cardsPacket3.getCards().add(card8);
        cardsPacket3.getCards().add(card9);

        theme1.getCardsPackets().add(cardsPacket1);
        theme1.getCardsPackets().add(cardsPacket2);
        theme2.getCardsPackets().add(cardsPacket3);

        themes.add(theme1);
        themes.add(theme2);

        String[] names = themes.stream().map(t -> t.getName()).toArray(String[]::new);
        TextPanel panel = new TextPanel("Темы", names, false);
        int choice = panel.getChoice();

        String[] packages = themes.get(choice - 1).getCardsPackets().stream().map(p -> p.getName()).toArray(String[]::new);
        TextPanel panel1 = new TextPanel("Пакеты::" + themes.get(choice - 1).getName(), packages, false);
        int choice1 = panel1.getChoice();


        }

}