package com.example.myapplication.View.Activities;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.View.Adapters.PacketsListViewAdapter;
import com.example.myapplication.R;

public class PacketsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packets);

        Bundle extras = getIntent().getExtras();
        int themeId = extras.getInt("themeId");

        ListView listView = findViewById(R.id.packetsListView);
        PacketsListViewAdapter packetsListViewAdapter = new PacketsListViewAdapter(this, Repository.getPackets(themeId));
        listView.setAdapter(packetsListViewAdapter);

    }


}