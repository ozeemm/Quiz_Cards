package Data;

import java.net.http.HttpClient;

public abstract class ServerWorker {
    protected final String url = "http://localhost:8080";
    protected HttpClient client = HttpClient.newHttpClient();
}
