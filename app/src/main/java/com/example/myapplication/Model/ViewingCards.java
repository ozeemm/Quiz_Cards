package com.example.myapplication.Model;

import java.util.ArrayList;

public class ViewingCards {
    private int currCardId=-1;
    private int knowCardsCount=0;
    private int studyCardsCounts=0;

    private ArrayList<Integer> studiedCards = new ArrayList<>();

    private boolean isRotate = false;

    private ArrayList<Card> cards = new ArrayList<>();

    public boolean getNextCard(){
        currCardId++;
        isRotate= false;
        if (currCardId>=cards.size())
            return false;
        return true;
    }

    public void addStudiedCard(){
        studiedCards.add(cards.get(currCardId).getId());
    }


    public String getCardText(){
        String cardText;
        if (isRotate)
            cardText = cards.get(currCardId).getBackText();
        else
            cardText =  cards.get(currCardId).getFrontText();
        return cardText;
    }
    public void rotateCard(){
        isRotate=!isRotate;
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


    public ArrayList<Integer> getStudiedCards() {
        return studiedCards;
    }

}
