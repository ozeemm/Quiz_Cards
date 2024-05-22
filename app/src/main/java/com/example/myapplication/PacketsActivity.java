package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Model.CardsPacket;

import java.util.ArrayList;

public class PacketsActivity extends AppCompatActivity {

    private int themeId;
    private ArrayList<CardsPacket> packets = new ArrayList<>();
     private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packets);

        Bundle extras = getIntent().getExtras();
        themeId = extras.getInt("themeId");
        packets = Repository.getPackets(themeId);

        listView = findViewById(R.id.packetsListView);
        PacketsListViewAdapter packetsListViewAdapter = new PacketsListViewAdapter(this,packets);
        listView.setAdapter(packetsListViewAdapter);

    }


}