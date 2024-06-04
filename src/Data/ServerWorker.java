package Data;

import java.net.http.HttpClient;

public abstract class ServerWorker {
    protected final String url = "http://localhost:8080";
    protected static HttpClient client = HttpClient.newHttpClient();
    protected static String jwtToken;

    protected String getAuthHeader(){
        return "Bearer " + jwtToken;
    }
}
