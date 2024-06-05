package com.example.myapplication.Data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class UserData {
    private HashMap<Integer, ArrayList<Integer>> knownCards  = new HashMap<>();

    public HashMap<Integer, ArrayList<Integer>> getKnownCards() {
        return knownCards;
    }
    public ArrayList<Integer> getKnownCards(int packetId) {
        if (!knownCards.containsKey(packetId))
            knownCards.put(packetId,new ArrayList<>());
        return knownCards.get(packetId);
    }

    public void addCards(int packetId, ArrayList<Integer> cards){
        if (!knownCards.containsKey(packetId))
            knownCards.put(packetId,new ArrayList<>());
        knownCards.get(packetId).addAll(cards);
    }

    public void setFromJson(String json){
        knownCards = new Gson().fromJson(json, new TypeToken<HashMap<Integer, ArrayList<Integer>>>(){}.getType());
    }
}
