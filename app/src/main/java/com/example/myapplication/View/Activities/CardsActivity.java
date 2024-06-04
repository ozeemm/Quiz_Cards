package com.example.myapplication.View.Activities;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.Model.ViewingCards;
import com.example.myapplication.R;

public class CardsActivity extends AppCompatActivity {

    private ViewingCards viewingCards = new ViewingCards();
    //private Card currCard = new Card();
    private TextView textView;
    private TextView textViewCount;
    private TextView textViewStudy;
    private TextView textViewKnow;
    private Button buttonKnow;
    private Button buttonStudy;

    Animation animTo;

    Animation animFrom;
    int packetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        Bundle extras = getIntent().getExtras();
        packetId = extras.getInt("packetId");

        iniComponents();

        Repository.getCards(packetId, cards -> runOnUiThread(() -> {
            viewingCards.setCards(cards);
            viewingCards.getNextCard();

            textViewCount.setText(viewingCards.getShownCardsCount()+"/"+viewingCards.getCardsCount());
            textView.setText(viewingCards.getCardText());
        }));

        textView.setOnClickListener(v -> {
            textView.startAnimation(animTo);
            viewingCards.rotateCard();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendStudiedCards();
    }

    private void viewingFinished(){
        textView.setText("Карточки закончены");
        textView.setEnabled(false);
        buttonKnow.setEnabled(false);
        buttonStudy.setEnabled(false);
    }

    private void iniComponents(){
        animTo = AnimationUtils.loadAnimation(this,R.anim.card_rotate_to_middle);
        animFrom = AnimationUtils.loadAnimation(this,R.anim.card_rotate_from_middle);
        animTo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.startAnimation(animFrom);
                textView.setText(viewingCards.getCardText());

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animFrom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


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
            viewingCards.addStudiedCard();
            textViewKnow.setText(""+viewingCards.getKnowCardsCount());
        }
        else {
            viewingCards.addStudyCardsCount();
            textViewStudy.setText(""+viewingCards.getStudyCardsCount());
        }
    }

    private void showNextCard(){
        boolean isCard = viewingCards.getNextCard();
        textViewCount.setText(viewingCards.getShownCardsCount()+"/"+viewingCards.getCardsCount());
        if (isCard){
            textView.setText(viewingCards.getCardText());
        }
        else{
            viewingFinished();
        }
    }

    private void sendStudiedCards(){
        Repository.setKnownCards(packetId, viewingCards.getStudiedCards());
    }


}