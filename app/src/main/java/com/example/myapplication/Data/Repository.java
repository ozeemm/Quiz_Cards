package com.example.myapplication.Data;

import com.example.myapplication.Interfaces.IGetCards;
import com.example.myapplication.Interfaces.IGetPackets;
import com.example.myapplication.Interfaces.IGetThemes;
import com.example.myapplication.Model.Card;
import com.example.myapplication.Model.CardsPacket;
import com.example.myapplication.Model.Theme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class  Repository {

    private static HTTPWorker httpWorker = new HTTPWorker();
    //private static ArrayList<Theme> themes = new ArrayList<>();
    private static ArrayList<CardsPacket> packets = new ArrayList<>();
    private static ArrayList<Card> cards = new ArrayList<>();
    private static UserData userData = new UserData();
    /*public static void initTestData(){
        // Это всё тестовое
        Theme theme1 = new Theme();
        theme1.setId(1);
        theme1.setName("Английский");
        Theme theme2 = new Theme();
        theme2.setId(2);
        theme2.setName("Обществознание");

        CardsPacket cardsPacket1 = new CardsPacket();
        cardsPacket1.setId(1);
        cardsPacket1.setThemeId(1);
        cardsPacket1.setName("Животные");

        CardsPacket cardsPacket2 = new CardsPacket();
        cardsPacket2.setId(2);
        cardsPacket2.setThemeId(1);
        cardsPacket2.setName("Профессии");

        CardsPacket cardsPacket3 = new CardsPacket();
        cardsPacket3.setId(3);
        cardsPacket3.setThemeId(2);
        cardsPacket3.setName("Право");

        Card card1 = new Card();
        card1.setId(1);
        card1.setPacketId(1);
        card1.setFrontText("cat");
        card1.setBackText("кошка");

        Card card2 = new Card();
        card2.setId(2);
        card2.setPacketId(1);
        card2.setFrontText("dog");
        card2.setBackText("собака");

        Card card3 = new Card();
        card3.setId(3);
        card3.setPacketId(1);
        card3.setFrontText("rabbit");
        card3.setBackText("кролик");

        Card card4 = new Card();
        card4.setId(4);
        card4.setPacketId(2);
        card4.setFrontText("teacher");
        card4.setBackText("учитель");

        Card card5 = new Card();
        card5.setId(5);
        card5.setPacketId(2);
        card5.setFrontText("driver");
        card5.setBackText("водитель");

        Card card6 = new Card();
        card6.setId(6);
        card6.setPacketId(2);
        card6.setFrontText("baker");
        card6.setBackText("пекарь");

        Card card7 = new Card();
        card7.setId(7);
        card7.setPacketId(3);
        card7.setFrontText("Частное право");
        card7.setBackText("право, регулирующее отношения между индивидуумами, коллективами");

        Card card8 = new Card();
        card8.setId(8);
        card8.setPacketId(3);
        card8.setFrontText("Завещание");
        card8.setBackText("односторонняя сделка, распоряжение своим имуществом на случай смерти");

        Card card9 = new Card();
        card9.setId(9);
        card9.setPacketId(3);
        card9.setFrontText("Суд");
        card9.setBackText("орган государства, осуществляющий правосудие");

        themes.add(theme1);
        themes.add(theme2);
        packets.add(cardsPacket1);
        packets.add(cardsPacket2);
        packets.add(cardsPacket3);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);
        cards.add(card9);
    }*/

    public static void getThemes(IGetThemes iGetThemes){
        httpWorker.getThemes(iGetThemes);
    }
    public static void getPackets(int themeId, IGetPackets iGetPackets){
        httpWorker.getPackets(themeId, iGetPackets);
    }
    public static void getCards(int packetId, IGetCards iGetCards){
        httpWorker.getCards(packetId, (cards) ->{
            ArrayList<Integer> knownCards = userData.getKnownCards(packetId);
            ArrayList<Card> neededCards = new ArrayList<>();
            cards.stream().filter(c->c.getPacketId()==packetId).filter(c-> !knownCards.contains(c.getId())).forEach(c->neededCards.add(c));
            iGetCards.onSuccess(neededCards);
        });
    }
    public static void restartCards(int packetId){
        userData.getKnownCards().clear();
    }

    public static void setKnownCards(int packetId, ArrayList<Integer> cards){
        userData.addCards(packetId,cards);
    }

    public static HashMap<Integer, ArrayList<Integer>> getKnownCards(){
        return userData.getKnownCards();
    }



}
