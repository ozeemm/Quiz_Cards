package com.example.myapplication.View.Activities;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.R;
import com.example.myapplication.View.Adapters.ThemesListViewAdapter;


public class ThemesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);

        ImageView imageViewExit = findViewById(R.id.imageViewExit);

        imageViewExit.setOnClickListener(v->{
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            Repository.delJwt();
            startActivity(intent);
        });

        Repository.getThemes(themes -> runOnUiThread(() -> {
            ListView listView = findViewById(R.id.themesListView);
            ThemesListViewAdapter themesListViewAdapter = new ThemesListViewAdapter(this, themes);
            listView.setAdapter(themesListViewAdapter);
        }));
    }

    public void onBackPressed() {
        super.onBackPressed();

    }
}


