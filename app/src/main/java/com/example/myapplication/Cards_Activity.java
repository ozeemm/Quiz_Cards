package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.Model.Card;
import com.example.myapplication.Model.ViewingCards;

import java.util.ArrayList;

public class Cards_Activity extends AppCompatActivity {

    ViewingCards viewingCards = new ViewingCards();
    ArrayList<Card> cards;
    int packetId;
    Card currCard = new Card();
    TextView textView;
    TextView textViewCount;
    TextView textViewStudy;
    TextView textViewKnow;
    Button buttonKnow;
    Button buttonStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        Bundle extras = getIntent().getExtras();
        packetId = extras.getInt("packetId");
        cards = Repository.getCards(packetId);

        viewingCards.setCards(cards);
        currCard = viewingCards.getNextCard();

        iniComponents();

        textViewCount.setText(viewingCards.getShownCardsCount()+"/"+viewingCards.getCardsCount());

        textView.setText(currCard.getFrontText());

        textView.setOnClickListener(v -> {
            if (textView.getText().equals(currCard.getFrontText()))
                textView.setText(currCard.getBackText());
            else
                textView.setText(currCard.getFrontText());

        });

        buttonKnow.setOnClickListener(v -> {
            addKnowOrNot(true);
            showNextCard();
        });

        buttonStudy.setOnClickListener(v -> {
            addKnowOrNot(false);
            showNextCard();
        });
    }

    private void viewingFinished(){
        textView.setText("Карточки закончены");
        textView.setEnabled(false);
        buttonKnow.setEnabled(false);
        buttonStudy.setEnabled(false);
    }

    private void iniComponents(){
        textView= findViewById(R.id.textView);
        textViewCount  = findViewById(R.id.textViewCount);
        textViewStudy = findViewById(R.id.textViewStudy);
        textViewKnow = findViewById(R.id.textViewKnow);
        buttonKnow = findViewById(R.id.buttonKnow);
        buttonStudy = findViewById(R.id.buttonStudy);
    }

    private void addKnowOrNot(boolean know){
        if (know){
            viewingCards.addKnowCardsCount();
            textViewKnow.setText(""+viewingCards.getKnowCardsCount());
        }
        else {
            viewingCards.addStudyCardsCount();
            textViewStudy.setText(""+viewingCards.getStudyCardsCount());
        }
    }

    public void showNextCard(){
        currCard = viewingCards.getNextCard();
        textViewCount.setText(viewingCards.getShownCardsCount()+"/"+viewingCards.getCardsCount());
        if (currCard != null){
            textView.setText(currCard.getFrontText());
        }
        else{
            viewingFinished();
        }
    }


}