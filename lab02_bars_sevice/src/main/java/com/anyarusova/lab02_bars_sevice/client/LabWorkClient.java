package com.anyarusova.lab02_bars_sevice.client;

import com.anyarusova.lab02_bars_sevice.dto.LabWorkData;
import com.anyarusova.lab02_bars_sevice.interceptor.ExtendedException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class LabWorkClient {
    public LabWorkData getLabWorkById(long id) throws ExtendedException {
        String serviceUrl = "https://localhost:8080/api/v1";     //TODO: add this url to config
        String url = serviceUrl + "/labworks/" + id;
        try {
            Client client = ClientBuilder.newClient();

            Response response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).get();

            LabWorkData labWorkData = response.readEntity(LabWorkData.class);

            client.close();

            if (labWorkData == null) {
                throw new ExtendedException(Response.Status.NOT_FOUND, "Лабораторная работа не найдена");
            }

            return labWorkData;

        } catch (ProcessingException e) {
            throw new ExtendedException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void updateLabWork(long id, LabWorkData labWorkData) throws ExtendedException {
        String serviceUrl = "https://localhost:8080/api/v1";     //TODO: add this url to config
        String url = serviceUrl + "/labworks/" + id;

        try {
            Client client = ClientBuilder.newClient();

            client.target(url).request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(labWorkData), LabWorkData.class);

            client.close();
        } catch (ProcessingException e) {
            throw new ExtendedException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
