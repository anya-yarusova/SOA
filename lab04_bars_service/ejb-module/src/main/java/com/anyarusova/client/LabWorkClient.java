package com.anyarusova.client;

import com.anyarusova.dto.LabWorkData;
import com.anyarusova.interceptor.ExtendedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LabWorkClient {

    String serviceUrl = "https://localhost:10002/api/v1";
    private final ObjectMapper objectMapper = new ObjectMapper();;

    public LabWorkData getLabWorkById(long id) throws ExtendedException {
        String url = serviceUrl + "/labworks/" + id;
        try {
            /*
            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", MediaType.APPLICATION_JSON)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            handleResponse(response);

            LabWorkData labWorkData = objectMapper.readValue(response.body(), LabWorkData.class);

            if (labWorkData == null) {
                throw new ExtendedException(Response.Status.NOT_FOUND, "Лабораторная работа не найдена");
            }

            return labWorkData;*/

            return LabWorkData.builder()
                    .name("Test Lab")
                    .coordinates(new LabWorkData.Coordinates(500.5f, 800.0))
                    .minimalPoint(150.0f)
                    .difficulty(LabWorkData.Difficulty.NORMAL)
                    .author(new LabWorkData.Person(
                            "Dr. John Smith",
                            Date.from( LocalDateTime.of(1980, 5, 15, 14, 30) .atZone(ZoneId.of("UTC")).toInstant()),
                            75.5,
                            new LabWorkData.Location(100.0f, 200, "Main Campus Lab")))
                    .build();

        } catch (Exception e) {
            throw new ExtendedException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void updateLabWork(long id, LabWorkData labWorkData) throws ExtendedException {
        String url = serviceUrl + "/labworks/" + id;

        try {
            /*
            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(labWorkData)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            handleResponse(response);
            */


        } catch (Exception e) {
            throw new ExtendedException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private static void handleResponse(HttpResponse<?> response) throws ExtendedException {
        if (response.statusCode() != Response.Status.OK.getStatusCode()) {
            throw new ExtendedException(Response.Status.BAD_REQUEST, "Лабораторная работа не найдена");
        }
    }
}
