package com.example.myapplication.Model;

import java.util.ArrayList;

public class ViewingCards {
    private int currCardId=-1;
    private int knowCardsCount=0;
    private int studyCardsCounts=0;

    private ArrayList<Card> cards = new ArrayList<>();

    public Card getNextCard(){
        currCardId++;
        if (currCardId>=cards.size())
            return null;
        return cards.get(currCardId);
    }
    public int getCardsCount(){
        return cards.size();
    }

    public int getKnowCardsCount(){
        return knowCardsCount;
    }

    public int getStudyCardsCount(){
        return studyCardsCounts;
    }

    public void addKnowCardsCount(){
        knowCardsCount++;
    }

    public void addStudyCardsCount(){
        studyCardsCounts++;
    }

    public int getShownCardsCount(){
        return knowCardsCount+studyCardsCounts;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
