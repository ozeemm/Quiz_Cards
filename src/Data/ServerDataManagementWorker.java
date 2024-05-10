package Data;

import Model.Card;
import Model.CardsPacket;
import Model.Theme;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerDataManagementWorker extends ServerWorker {
    private final String dataManagementUrl = url + "/api/data-management";

    public void createTheme(Theme theme){
        try{
            String requestBody = new Gson().toJson(theme);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataManagementUrl+"/theme"))
                    .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch(Exception e){ e.printStackTrace(); }
    }
    public void updateTheme(Theme theme){
        try {
            String requestBody = new Gson().toJson(theme);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataManagementUrl+"/theme"))
                    .method("PUT", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e){ e.printStackTrace(); }
    }
    public void deleteTheme(Theme theme){
        try {
            String requestBody = new Gson().toJson(theme);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataManagementUrl+"/theme"))
                    .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e){ e.printStackTrace(); }
    }

    public void createPacket(CardsPacket packet){
        try{
            String requestBody = new Gson().toJson(packet);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataManagementUrl+"/packet"))
                    .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch(Exception e){ e.printStackTrace(); }
    }
    public void updatePacket(CardsPacket packet){
        try {
            String requestBody = new Gson().toJson(packet);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataManagementUrl+"/packet"))
                    .method("PUT", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e){ e.printStackTrace(); }
    }
    public void deletePacket(CardsPacket packet){
        try {
            String requestBody = new Gson().toJson(packet);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataManagementUrl+"/packet"))
                    .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e){ e.printStackTrace(); }
    }

    public void createCard(Card card){
        try{
            String requestBody = new Gson().toJson(card);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataManagementUrl+"/card"))
                    .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch(Exception e){ e.printStackTrace(); }
    }
    public void updateCard(Card card){
        try {
            String requestBody = new Gson().toJson(card);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataManagementUrl+"/card"))
                    .method("PUT", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e){ e.printStackTrace(); }
    }
    public void deleteCard(Card card){
        try {
            String requestBody = new Gson().toJson(card);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(dataManagementUrl+"/card"))
                    .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e){ e.printStackTrace(); }
    }
}
