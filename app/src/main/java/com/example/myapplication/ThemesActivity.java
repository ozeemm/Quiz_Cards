package com.example.myapplication;

import android.content.Intent;
import android.view.View;
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

        Repository.initTestData();
        themes = Repository.getThemes();

        listView = findViewById(R.id.themesListView);
        ThemesListViewAdapter themesListViewAdapter = new ThemesListViewAdapter(this,themes);
        listView.setAdapter(themesListViewAdapter);

    }


}