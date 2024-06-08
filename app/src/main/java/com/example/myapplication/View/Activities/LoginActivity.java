package com.example.myapplication.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Data.Repository;
import com.example.myapplication.R;


public class LoginActivity extends AppCompatActivity {

    private Button buttonSignIn;
    private Button buttonSignUp;
    private EditText email;
    private EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Repository.delJwt();

        Repository.signIn((isSignedIn)-> {
            sign(isSignedIn, false);
        });


        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPas);

        buttonSignIn.setOnClickListener(v->{
            if (!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
                Toast toast = Toast.makeText(this, "Неверный формат email-адреса", Toast.LENGTH_LONG);
                toast.show();
            }
            else{
                Repository.signIn(email.getText().toString(), password.getText().toString(), (isSignedIn)-> {
                    sign(isSignedIn, true);
                });
            }
        });

        buttonSignUp.setOnClickListener(v->{
            if (!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
                Toast toast = Toast.makeText(this, "Неверный формат email-адреса", Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                Repository.signUp(email.getText().toString(), password.getText().toString(), (isSignedUp)-> {
                    if (!isSignedUp){
                        runOnUiThread(()->{
                            Toast toast = Toast.makeText(this, "Такой пользователь уже существует", Toast.LENGTH_LONG);
                            toast.show();
                        });
                    }
                    else {
                        Repository.signIn(email.getText().toString(), password.getText().toString(), (isSignedIn -> {
                            sign(isSignedIn, true);
                        }));
                    }
                });
            }


        });

    }

    private void sign(boolean isSignedIn, boolean enableMessage){
        if(isSignedIn) {
            Intent intent = new Intent(this, ThemesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            this.finish();
        }
        else {
            if(enableMessage){
                runOnUiThread(()->{
                    Toast toast = Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_LONG);
                    toast.show();
                });
            }
        }
    }



}


