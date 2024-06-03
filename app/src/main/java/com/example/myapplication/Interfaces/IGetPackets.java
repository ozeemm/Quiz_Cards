package com.example.myapplication.Interfaces;

import com.example.myapplication.Model.CardsPacket;

import java.util.ArrayList;

public interface IGetPackets {
    void onSuccess(ArrayList<CardsPacket> packets);
}
