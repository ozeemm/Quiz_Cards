package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            TextView textView = findViewById(R.id.textView);
            textView.setText("Hello Kitty!");

            TextView textViewStudy = findViewById(R.id.textViewStudy);
            TextView textViewKnow = findViewById(R.id.textViewKnow);

            Button buttonKnow = findViewById(R.id.buttonKnow);
            Button buttonLearn = findViewById(R.id.buttonStudy);

            buttonKnow.setOnClickListener(v -> {
                textView.setText("I know Hello Kitty!");
                Integer knowCount = Integer.parseInt((String) textViewKnow.getText())+1;
                String knowCountString = knowCount.toString();
                textViewKnow.setText(knowCountString);
            });

            buttonLearn.setOnClickListener(v -> {
                textView.setText("I study with Hello Kitty!");
                Integer studyCount = Integer.parseInt((String) textViewStudy.getText())+1;
                String studyCountString = studyCount.toString();
                textViewStudy.setText(studyCountString);
            });

        }



}