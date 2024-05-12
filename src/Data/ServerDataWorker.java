package Data;

import Model.Card;
import Model.CardsPacket;
import Model.Theme;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerDataWorker extends ServerWorker{
    private final String dataUrl = url + "/api/data";

    public ArrayList<Theme> getThemes(){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataUrl+"/themes"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Theme[] themesList = new Gson().fromJson(response.body(), Theme[].class);
            return new ArrayList<Theme>(Arrays.asList(themesList));
        } catch (Exception e){ e.printStackTrace(); }
        return null;
    }
    public int getThemesCount(){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataUrl+"/themes/count"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return new Gson().fromJson(response.body(), Integer.class);
        } catch(Exception e){ e.printStackTrace(); }
        return -1;
    }

    public ArrayList<CardsPacket> getPackets(int themeId){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataUrl+"/packets?theme="+themeId))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            CardsPacket[] packetList = new Gson().fromJson(httpResponse.body(), CardsPacket[].class);
            return new ArrayList<CardsPacket>(Arrays.asList(packetList));
        } catch (Exception e){ e.printStackTrace(); }
        return null;
    }
    public int getPacketsCount(int themeId){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataUrl+"/packets/count?theme="+themeId))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Integer.class);
        } catch(Exception e){ e.printStackTrace(); }
        return -1;
    }

    public ArrayList<Card> getCards(int packetId){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataUrl+"/cards?packet="+packetId))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            Card[] cardsList = new Gson().fromJson(httpResponse.body(), Card[].class);
            return new ArrayList<Card>(Arrays.asList(cardsList));
        } catch (Exception e){ e.printStackTrace(); }
        return null;
    }
    public int getCardsCount(int packetId){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataUrl+"/cards/count?packet="+packetId))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Integer.class);
        } catch (Exception e){ e.printStackTrace(); }
        return -1;
    }
}
