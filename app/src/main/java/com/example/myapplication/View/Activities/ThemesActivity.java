package com.example.myapplication.View.Activities;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.Interfaces.IGetThemes;
import com.example.myapplication.Model.Theme;
import com.example.myapplication.R;
import com.example.myapplication.View.Adapters.ThemesListViewAdapter;

import java.util.ArrayList;


public class ThemesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);

        Repository.getThemes(themes -> runOnUiThread(() -> {
            ListView listView = findViewById(R.id.themesListView);
            ThemesListViewAdapter themesListViewAdapter = new ThemesListViewAdapter(this, themes);
            listView.setAdapter(themesListViewAdapter);
        }));
    }

}


