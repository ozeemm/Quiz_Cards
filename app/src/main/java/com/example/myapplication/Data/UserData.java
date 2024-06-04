package com.example.myapplication.Data;

import com.example.myapplication.Model.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

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


}
