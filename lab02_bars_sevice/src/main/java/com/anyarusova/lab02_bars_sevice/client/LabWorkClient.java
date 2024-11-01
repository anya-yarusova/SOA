package com.anyarusova.lab02_bars_sevice.client;

import com.anyarusova.lab02_bars_sevice.dto.LabWorkData;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class LabWorkClient {
    public LabWorkData getLabWorkById(long id){
        String serviceUrl = "https://localhost:8080/api/v1";     //TODO: add this url to config
        String url = serviceUrl + "/labworks/" + id;
        try {
            Client client = ClientBuilder.newClient();

            Response response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).get();

            LabWorkData labWorkData = response.readEntity(LabWorkData.class);

            client.close();

            return labWorkData;

        } catch (ProcessingException e) {
            return null;
        }
    }

    public void updateLabWork(long id, LabWorkData labWorkData) {
        String serviceUrl = "https://localhost:8080/api/v1";     //TODO: add this url to config
        String url = serviceUrl + "/labworks/" + id;
        Client client = ClientBuilder.newClient();

        client.target(url).request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(labWorkData), LabWorkData.class);

        client.close();
    }
}
