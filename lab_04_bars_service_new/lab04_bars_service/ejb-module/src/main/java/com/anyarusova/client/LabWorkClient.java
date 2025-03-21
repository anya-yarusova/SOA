package com.anyarusova.client;

import com.anyarusova.dto.LabWorkData;
import com.anyarusova.interceptor.ExtendedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;

public class LabWorkClient {

    String serviceUrl = "https://localhost:24458/api/v1";
    String certPath = "/home/vezhur/lab3_without_mule/c/keystore.jks";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public LabWorkData getLabWorkById(long id) throws ExtendedException {
        String uri = serviceUrl + "/labworks/" + id;

        try {
            // Load keystore (this is where your SSL certificate is stored)
            KeyStore keyStore = KeyStore.getInstance("JKS");
            try (InputStream keyStoreStream = new FileInputStream(certPath)) {
                if (keyStoreStream == null) {
                    throw new RuntimeException("Keystore not found");
                }
                keyStore.load(keyStoreStream, "secret".toCharArray());  // Replace "changeit" with your keystore password
            }

            // Build SSLContext using the keystore
            SSLContext sslContext = SSLContextBuilder.create()
                    .loadKeyMaterial(keyStore, "secret".toCharArray())  // Load key material (certificate)
                    .loadTrustMaterial(keyStore, null)  // Trust all certs in the keystore
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            // Prepare HttpClient with SSL and NoopHostnameVerifier (disabling hostname verification for simplicity)
            try (CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build()) {
                HttpGet httpGet = new HttpGet(uri);
                HttpResponse response = httpClient.execute(httpGet);
                String responseString = EntityUtils.toString(response.getEntity());
                int statusCode = response.getStatusLine().getStatusCode();
                handleStatusCode(statusCode);
                LabWorkData labWorkData = objectMapper.readValue(responseString, LabWorkData.class);
                if (labWorkData == null) {
                    throw new ExtendedException(Response.Status.NOT_FOUND, "Лабораторная работа не найдена");
                }

                return labWorkData;
            } catch (Exception e) {
                throw e;
            }
        }  catch (Exception e) {
            // Log exception (can also improve error handling here)
            e.printStackTrace();
            throw new ExtendedException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        // return LabWorkData.builder()
        //         .name("Test Lab")
        //         .coordinates(new LabWorkData.Coordinates(500.5f, 800.0))
        //         .minimalPoint(150.0f)
        //         .difficulty(LabWorkData.Difficulty.NORMAL)
        //         .author(new LabWorkData.Person(
        //                 "Dr. John Smith",
        //                 Date.from( LocalDateTime.of(1980, 5, 15, 14, 30) .atZone(ZoneId.of("UTC")).toInstant()),
        //                 75.5,
        //                 new LabWorkData.Location(100.0f, 200, "Main Campus Lab")))
        //         .build();
    }

    public void updateLabWork(long id, LabWorkData labWorkData) throws ExtendedException {
        String uri = serviceUrl + "/labworks/" + id;

        try {
            // Load keystore (this is where your SSL certificate is stored)
            KeyStore keyStore = KeyStore.getInstance("JKS");
            try (InputStream keyStoreStream = new FileInputStream(certPath)) {
                if (keyStoreStream == null) {
                    throw new RuntimeException("Keystore not found");
                }
                keyStore.load(keyStoreStream, "secret".toCharArray());  // Replace "changeit" with your keystore password
            }

            // Build SSLContext using the keystore
            SSLContext sslContext = SSLContextBuilder.create()
                    .loadKeyMaterial(keyStore, "secret".toCharArray())  // Load key material (certificate)
                    .loadTrustMaterial(keyStore, null)  // Trust all certs in the keystore
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            // Prepare HttpClient with SSL and NoopHostnameVerifier (disabling hostname verification for simplicity)
            try (CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build()) {
                HttpPut httpPut = new HttpPut(uri);
                String body = objectMapper.writeValueAsString(labWorkData);
                httpPut.setEntity(new StringEntity(body, "UTF-8"));
                httpPut.setHeader("content-type", "application/json;charset=UTF-8");
                HttpResponse response = httpClient.execute(httpPut);
                String responseString = EntityUtils.toString(response.getEntity());
                int statusCode = response.getStatusLine().getStatusCode();
                handleStatusCode(statusCode);                // LabWorkData labWorkDataUpdated = objectMapper.readValue(responseString, LabWorkData.class);
                // if (labWorkDataUpdated == null) {
                //     throw new ExtendedException(Response.Status.NOT_FOUND, "Лабораторная работа не найдена");
                // }

                // return labWorkDataUpdated;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw new ExtendedException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private static void handleStatusCode(int code) throws ExtendedException {
        if (code != Response.Status.OK.getStatusCode()) {
            throw new ExtendedException(Response.Status.BAD_REQUEST, "Лабораторная работа не найдена");
        }
    }
}
