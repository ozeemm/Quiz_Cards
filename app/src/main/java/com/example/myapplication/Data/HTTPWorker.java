package com.example.myapplication.Data;

import com.example.myapplication.Model.Card;
import com.example.myapplication.Model.CardsPacket;
import com.example.myapplication.Model.Theme;
import com.google.gson.Gson;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;

public class HTTPWorker {

    OkHttpClient client = new OkHttpClient();
    String url = "http://192.168.1.8:8080/api";
    String dataUrl = url + "/data";
    String userUrl = url + "/user";

    private String jwtToken;
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void getThemes(Consumer<ArrayList<Theme>> consumer){
        Request request = new Request.Builder().url(dataUrl+"/themes").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Theme[] themes = new Gson().fromJson(response.body().string(), Theme[].class);
                consumer.accept(new ArrayList<Theme>(Arrays.asList(themes)));
            }
        });
    }

    public void getPackets(int themeId, Consumer<ArrayList<CardsPacket>> consumer) {
        Request request = new Request.Builder().url(dataUrl + "/packets?theme=" + themeId).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                CardsPacket[] packets = new Gson().fromJson(response.body().string(), CardsPacket[].class);
                consumer.accept(new ArrayList<CardsPacket>(Arrays.asList(packets)));
            }
        });
    }

    public void getCards(int packetId, Consumer<ArrayList<Card>> consumer) {
        Request request = new Request.Builder().url(dataUrl + "/cards?packet=" + packetId).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Card[] cards = new Gson().fromJson(response.body().string(), Card[].class);
                consumer.accept(new ArrayList<Card>(Arrays.asList(cards)));
            }
        });
    }

    public void signUp(String email, String password, Consumer<Integer> consumer){
        try {
            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("password", password);

            RequestBody body = RequestBody.create(json.toString(), JSON);
            Request request = new Request.Builder()
                    .url(userUrl+"/register")
                    .post(body)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) { e.printStackTrace(); }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    consumer.accept(response.code());
                }
            });
        } catch (JSONException e) {}
    }

    public void signIn(String email, String password, Consumer<Response> consumer){
        try {
            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("password", password);

            RequestBody body = RequestBody.create(json.toString(), JSON);
            Request request = new Request.Builder()
                    .url(userUrl+"/login")
                    .post(body)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) { e.printStackTrace(); }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if(response.code() == 200) {
                        jwtToken = response.body().string();
                    }
                    consumer.accept(response);
                }
            });
        } catch (JSONException e) {}
    }

    public void getUserData(Consumer<String> consumer){
        Request request = new Request.Builder()
                .url(userUrl+"/data")
                .header("Authorization", "Bearer "+jwtToken)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) { e.printStackTrace(); }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String userdata = response.body().string();
                consumer.accept(userdata);
            }
        });
    }

    public void updateUserData(HashMap<Integer, ArrayList<Integer>> userData){
        String json = new Gson().toJson(userData);
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(userUrl+"/data")
                .header("Authorization", "Bearer "+jwtToken)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // Если авторизация слетит, ошибка будет тут
            }
        });
    }
}
