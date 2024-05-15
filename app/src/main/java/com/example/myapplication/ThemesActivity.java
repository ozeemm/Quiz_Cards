package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.Model.Theme;

import java.util.ArrayList;

public class ThemesActivity extends AppCompatActivity {


    ArrayList<Theme> themes = new ArrayList<>();

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);

        Theme theme1 = new Theme();
        theme1.setName("Английский");
        theme1.setDescription("Пакеты про английский");
        themes.add(theme1);

        Theme theme2 = new Theme();
        theme2.setName("Обществознание");
        theme2.setDescription("Пакеты про обществознание");
        themes.add(theme2);

        themes.add(theme2);
        themes.add(theme2);
        themes.add(theme2);
        themes.add(theme2);
        themes.add(theme2);
        themes.add(theme2);
        themes.add(theme2);
        themes.add(theme2);
        themes.add(theme2);
        themes.add(theme2);

        listView = findViewById(R.id.listView);
        ListViewAdapter listViewAdapter = new ListViewAdapter(getApplicationContext(),themes);
        listView.setAdapter(listViewAdapter);

    }

    public void showNextActivity(View v){
        Intent intent = new Intent(this, Cards_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}