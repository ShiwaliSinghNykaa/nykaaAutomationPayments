package com.browserstack.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T get(String url, Class<T> responseType) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        checkStatus(response);

        return objectMapper.readValue(response.body(), responseType);
    }

    public <T> T post(String url, Object requestBody, Class<T> responseType) throws IOException, InterruptedException {
        String jsonBody = objectMapper.writeValueAsString(requestBody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        checkStatus(response);

        return objectMapper.readValue(response.body(), responseType);
    }

    public <T> T patch(String url, Object requestBody, Class<T> responseType) throws IOException, InterruptedException {
        String jsonBody = objectMapper.writeValueAsString(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        checkStatus(response);

        return objectMapper.readValue(response.body(), responseType);
    }

    private void checkStatus(HttpResponse<String> response) throws IOException {
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException(
                    "Request failed with status: " + response.statusCode() + " and body: " + response.body());
        }
    }
}
