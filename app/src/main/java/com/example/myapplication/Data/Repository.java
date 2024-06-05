package com.example.myapplication.Data;

import com.example.myapplication.Model.Card;
import com.example.myapplication.Model.CardsPacket;
import com.example.myapplication.Model.Theme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class  Repository {

    private static HTTPWorker httpWorker = new HTTPWorker();
    //private static ArrayList<Theme> themes = new ArrayList<>();
    private static ArrayList<CardsPacket> packets = new ArrayList<>();
    private static ArrayList<Card> cards = new ArrayList<>();
    private static UserData userData = new UserData();

    public static void getThemes(Consumer<ArrayList<Theme>> consumer){
        httpWorker.getThemes(consumer);
    }
    public static void getPackets(int themeId, Consumer<ArrayList<CardsPacket>> consumer){
        httpWorker.getPackets(themeId, consumer);
    }
    public static void getCards(int packetId, Consumer<ArrayList<Card>> consumer){
        httpWorker.getCards(packetId, (cards) ->{
            ArrayList<Integer> knownCards = userData.getKnownCards(packetId);
            ArrayList<Card> neededCards = new ArrayList<>();
            cards.stream().filter(c->c.getPacketId()==packetId).filter(c-> !knownCards.contains(c.getId())).forEach(c->neededCards.add(c));
            consumer.accept(neededCards);
        });
    }
    public static void signUp(String email, String password, Consumer<Boolean> consumer){
        httpWorker.signUp(email, password, (status)->{
            if(status == 400){
                consumer.accept(false);
            } else if(status == 200){
                consumer.accept(true);
            }
        });
    }

    public static void signIn(String email, String password, Consumer<Boolean> consumer){
        httpWorker.signIn(email, password, (response -> {
            if(response.code() == 200) {
                getUserData();
                consumer.accept(true);
            }
            else if(response.code()==401){
                consumer.accept(false);
            }
        }));
    }
    public static void getUserData(){
        httpWorker.getUserData((str) -> {
            if(str != ""){
                userData.setFromJson(str);
            }
        });
    }
    public static void restartCards(int packetId){
        userData.getKnownCards().get(packetId).clear();
    }

    public static void setKnownCards(int packetId, ArrayList<Integer> cards){
        userData.addCards(packetId,cards);
        httpWorker.updateUserData(userData.getKnownCards());
    }

    public static HashMap<Integer, ArrayList<Integer>> getKnownCards(){
        return userData.getKnownCards();
    }



}
