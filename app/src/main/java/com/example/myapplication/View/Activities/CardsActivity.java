package com.example.myapplication.View.Activities;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.Model.Card;
import com.example.myapplication.Model.ViewingCards;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CardsActivity extends AppCompatActivity {

    private ViewingCards viewingCards = new ViewingCards();
    private Card currCard = new Card();
    private TextView textView;
    private TextView textViewCount;
    private TextView textViewStudy;
    private TextView textViewKnow;
    private Button buttonKnow;
    private Button buttonStudy;

    int packetId;
    private ArrayList<Integer> studiedCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        Bundle extras = getIntent().getExtras();
        packetId = extras.getInt("packetId");

        viewingCards.setCards(Repository.getCards(packetId));
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
            studiedCards.add(currCard.getId());
            textViewKnow.setText(""+viewingCards.getKnowCardsCount());
        }
        else {
            viewingCards.addStudyCardsCount();
            textViewStudy.setText(""+viewingCards.getStudyCardsCount());
        }
    }

    private void showNextCard(){
        currCard = viewingCards.getNextCard();
        textViewCount.setText(viewingCards.getShownCardsCount()+"/"+viewingCards.getCardsCount());
        if (currCard != null){
            textView.setText(currCard.getFrontText());
        }
        else{
            viewingFinished();
            sendStudiedCards();
        }
    }

    private void sendStudiedCards(){
        Repository.setKnownCards(packetId, studiedCards);
    }


}