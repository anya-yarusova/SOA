package com.anyarusova.lab02_bars_sevice.client;

import com.anyarusova.lab02_bars_sevice.dto.LabWorkData;
import com.anyarusova.lab02_bars_sevice.interceptor.ExtendedException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class LabWorkClient {
    public LabWorkData getLabWorkById(long id) throws ExtendedException {
        String serviceUrl = "http://localhost:5678/v1";     //TODO: add this url to config
        String url = serviceUrl + "/labworks/" + id;
        try {
            Client client = ClientBuilder.newClient();

            Response response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).get();

            handleResponse(response);

            LabWorkData labWorkData = response.readEntity(LabWorkData.class);

            client.close();

            if (labWorkData == null) {
                throw new ExtendedException(Response.Status.NOT_FOUND, "Лабораторная работа не найдена");
            }

            return labWorkData;

        } catch (Exception e) {
            throw new ExtendedException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void updateLabWork(long id, LabWorkData labWorkData) throws ExtendedException {
        String serviceUrl = "http://localhost:5678/v1";     //TODO: add this url to config
        String url = serviceUrl + "/labworks/" + id;

        try {
            Client client = ClientBuilder.newClient();

            Response response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(labWorkData));

            handleResponse(response);

            client.close();
        } catch (Exception e) {
            throw new ExtendedException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private static void handleResponse(Response response) throws ExtendedException {
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new ExtendedException(Response.Status.BAD_REQUEST, "Лабораторная работа не найдена");
        }
    }
}
