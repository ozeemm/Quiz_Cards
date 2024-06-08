package com.example.myapplication.Data;
import android.content.Context;
import android.content.SharedPreferences;
import android.app.Application;

public class PreferenceWorker extends Application {
    private static final String STORAGE_NAME = "StorageName";
    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;

    @Override
    public void onCreate() {
        super.onCreate();
        settings = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

    }

    public void setJwt(String jwtToken){
        editor.putString("jwtToken", jwtToken);
        editor.commit();
    }

    public String getJwt(){
        return settings.getString("jwtToken", null );
    }

    public void delJwt(){
        editor.remove("jwtToken").commit();
    }


}

