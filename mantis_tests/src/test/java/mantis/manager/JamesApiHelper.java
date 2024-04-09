package mantis.manager;

import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;

public class JamesApiHelper extends HelperBase {
    MediaType JSON = MediaType.get("application/json");
    OkHttpClient client;


    public JamesApiHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void addUser(String email, String password) {

        RequestBody body = RequestBody.create(
                String.format("{\"password\":\"%s\"}", password), JSON);

        Request request = new Request.Builder()
                .url(String.format("%s/users/%s", manager.property("james.apiBaseUrl"), email))
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
