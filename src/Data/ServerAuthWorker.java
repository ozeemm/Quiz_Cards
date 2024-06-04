package Data;

import Model.Theme;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerAuthWorker extends ServerWorker {
    private final String authUrl = url + "/api/user";
    public boolean Authenticate(String email, String password){
        try{
            JsonObject json = new JsonObject();
            json.addProperty("email", email);
            json.addProperty("password", password);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(authUrl+"/login"))
                    .method("POST", HttpRequest.BodyPublishers.ofString(json.toString()))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(httpResponse.statusCode() == 200){
                jwtToken = httpResponse.body();
                return true;
            }
            else return false;

        } catch(Exception e){ e.printStackTrace(); }
        return false;
    }
}
