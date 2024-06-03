package com.example.myapplication.Interfaces;

import com.example.myapplication.Model.Card;

import java.util.ArrayList;

public interface IGetCards {
    void onSuccess(ArrayList<Card> cards);
}
