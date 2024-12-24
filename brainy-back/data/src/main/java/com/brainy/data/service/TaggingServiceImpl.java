package com.brainy.data.service;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class TaggingServiceImpl {

    private static final String API_URL = "https://api.textrazor.com/";
    private static final String API_KEY = "335d564b2970c637413611161055a0dd40a428753f9696847432951f";

    public String[] generateTags(String text) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String requestBody = "text=" + text + "&extractors=topics"
                + "&score_threshold=1.0"  // Filtro per punteggio minimo
                + "&topic_limit=5";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("x-textrazor-key", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());

        if (!jsonResponse.getJSONObject("response").has("topics")) {
            return new String[0]; // Restituisce array vuoto se non ci sono topic
        }
        JSONArray topics = jsonResponse.getJSONObject("response").getJSONArray("topics");

        String[] tags = new String[topics.length()];
        for (int i = 0; i < topics.length(); i++) {
            tags[i] = topics.getJSONObject(i).getString("label");
        }
        return tags;
    }
}
