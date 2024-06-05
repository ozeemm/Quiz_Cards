package com.example.myapplication.View.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.View.Adapters.PacketsListViewAdapter;
import com.example.myapplication.R;

public class PacketsActivity extends AppCompatActivity {

    int themeId;
    PacketsListViewAdapter packetsListViewAdapter;
    ListView listView;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packets);

        Bundle extras = getIntent().getExtras();
        themeId = extras.getInt("themeId");
        String themeName = extras.getString("themeName");

        TextView textViewThemeName = findViewById(R.id.textViewPacketName);
        textViewThemeName.setText(textViewThemeName.getText()+" "+themeName);

        imageView = findViewById(R.id.imageViewBack);

        imageView.setOnClickListener(v -> {
            onBackPressed();
        });


        Repository.getPackets(themeId, packets -> runOnUiThread(() -> {
            listView = findViewById(R.id.packetsListView);
            packetsListViewAdapter = new PacketsListViewAdapter(this, packets, Repository.getKnownCards());
            listView.setAdapter(packetsListViewAdapter);
        }));




    }

    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onRestart(){
        super.onRestart();
        Repository.getPackets(themeId, packets -> runOnUiThread(() -> {
            packetsListViewAdapter = new PacketsListViewAdapter(this, packets, Repository.getKnownCards());
            listView.setAdapter(packetsListViewAdapter);
        }));
    }


}