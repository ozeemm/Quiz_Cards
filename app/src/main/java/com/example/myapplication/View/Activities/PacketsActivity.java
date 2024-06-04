package com.example.myapplication.View.Activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.View.Adapters.PacketsListViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.View.Adapters.ThemesListViewAdapter;

public class PacketsActivity extends AppCompatActivity {

    int themeId;
    PacketsListViewAdapter packetsListViewAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packets);

        Bundle extras = getIntent().getExtras();
        themeId = extras.getInt("themeId");
        String themeName = extras.getString("themeName");

        TextView textViewThemeName = findViewById(R.id.textViewThemeName);
        textViewThemeName.setText(textViewThemeName.getText()+" "+themeName);

        Repository.getPackets(themeId, packets -> runOnUiThread(() -> {
            listView = findViewById(R.id.packetsListView);
            packetsListViewAdapter = new PacketsListViewAdapter(this, packets, Repository.getKnownCards());
            listView.setAdapter(packetsListViewAdapter);
        }));




    }

    @Override
    public void onRestart(){
        super.onRestart();
        packetsListViewAdapter.notifyDataSetChanged();
    }


}