package com.example.myapplication.Data;

import com.example.myapplication.Interfaces.IGetThemes;
import com.example.myapplication.Model.Theme;
import com.google.gson.Gson;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HTTPWorker {

    OkHttpClient client = new OkHttpClient();
    String url = "http://192.168.1.8:8080/api/data";

    public void getThemes(IGetThemes iGetThemes){
        Request request = new Request.Builder().url(url+"/themes").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Theme[] themes = new Gson().fromJson(response.body().string(), Theme[].class);
                iGetThemes.onSuccess(new ArrayList<Theme>(Arrays.asList(themes)));
            }
        });
    }
}
