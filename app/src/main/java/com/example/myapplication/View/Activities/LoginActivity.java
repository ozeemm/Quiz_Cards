package com.example.myapplication.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.R;
import com.example.myapplication.View.Adapters.ThemesListViewAdapter;


public class LoginActivity extends AppCompatActivity {

    Button buttonEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonEnter = findViewById(R.id.buttonEnter);

        buttonEnter.setOnClickListener(v->{
            Intent intent = new Intent(this, ThemesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            this.finish();
        });
    }

}


